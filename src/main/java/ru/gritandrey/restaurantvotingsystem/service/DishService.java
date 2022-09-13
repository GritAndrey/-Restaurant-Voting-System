package ru.gritandrey.restaurantvotingsystem.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.repository.querydsl.QPredicates;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;
import ru.gritandrey.restaurantvotingsystem.to.MenuTo;
import ru.gritandrey.restaurantvotingsystem.util.DishUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static ru.gritandrey.restaurantvotingsystem.model.QDish.dish;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public Dish get(int dishId, int restaurantId) {
        return dishRepository.checkBelong(dishId, restaurantId);
    }

    @Cacheable(value = "menus",
            condition = ("#dishFilter.startDate != null && #dishFilter.startDate.equals(T(java.time.LocalDate).now())")
            , keyGenerator = "DishFilterTodayKeyGenerator")
    public List<MenuTo> getByFilter(DishFilter dishFilter) {
        final Predicate predicate = getPredicate(dishFilter);
        final Iterable<Dish> filteredDishes = dishRepository.findAll(predicate);
        List<Dish> dishes = new ArrayList<>();
        filteredDishes.forEach(dishes::add);
        return getMenuTos(dishes);
    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", key = "#restaurantId")
    })
    public Dish create(Dish dish, Integer restaurantId) {
        return save(dish, restaurantId);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", key = "#restaurantId")})
    public void update(Dish updated, Integer updatedId, Integer restaurantId) {
        final var dish = dishRepository.checkBelong(updatedId, restaurantId);
        dish.setName(updated.getName());
        dish.setPrice(updated.getPrice());
    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", key = "#restaurantId")})
    public void delete(int id, int restaurantId) {
        dishRepository.delete(dishRepository.checkBelong(id, restaurantId));
    }

    @Transactional
    protected Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(dish);
    }

    private Predicate getPredicate(DishFilter dishFilter) {
        return QPredicates.builder()
                .add(dishFilter.restaurantId(), dish.restaurant.id::eq)
                .add(dishFilter.startDate(), dish.date::goe)
                .add(dishFilter.endDate(), dish.date::loe)
                .build();
    }

    private List<MenuTo> getMenuTos(List<Dish> filteredDishes) {
        record MenuKey(LocalDate menuDate,
                       Integer restaurantId) {
        }
        final Map<MenuKey, List<Dish>> groupedByDateAndRestaurantId = filteredDishes.stream()
                .collect(groupingBy(dish -> new MenuKey(dish.getDate(), dish.getRestaurant().getId())));
        return groupedByDateAndRestaurantId.entrySet().stream()
                .map(entry -> MenuTo.builder()
                        .restaurantId(entry.getKey().restaurantId())
                        .menuDate(entry.getKey().menuDate())
                        .dishes(DishUtil.getTos(entry.getValue()))
                        .build()
                ).sorted(Comparator.comparing(MenuTo::getMenuDate).reversed().thenComparing(MenuTo::getRestaurantId)).collect(toList());
    }
}
