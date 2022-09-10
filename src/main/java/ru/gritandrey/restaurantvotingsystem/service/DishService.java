package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;
import ru.gritandrey.restaurantvotingsystem.to.MenuTo;
import ru.gritandrey.restaurantvotingsystem.util.DishUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public Dish get(int dishId, int restaurantId) {
        return dishRepository.checkBelong(dishId, restaurantId);
    }

    @Cacheable(value = "menus", condition = ("#dishFilter.startDate != null && #dishFilter.startDate.equals(T(java.time.LocalDate).now())"))
    public List<MenuTo> getByFilter(DishFilter dishFilter) {
        final var filteredDishes = dishRepository.findAllByFilter(dishFilter);
        record MenuKey(LocalDate menuDate,
                       Integer restaurantId) {
        }
        final var groupedByDateAndRestaurantId = filteredDishes.stream()
                .collect(groupingBy(dish -> new MenuKey(dish.getDate(), dish.getRestaurant().getId())));
        return groupedByDateAndRestaurantId.entrySet().stream()
                .map(entry -> MenuTo.builder()
                        .restaurantId(entry.getKey().restaurantId())
                        .menuDate(entry.getKey().menuDate())
                        .dishes(DishUtil.getTos(entry.getValue()))
                        .build()
                ).sorted(Comparator.comparing(MenuTo::getMenuDate).reversed().thenComparing(MenuTo::getRestaurantId)).collect(toList());

    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)
    })
    public Dish create(Dish dish, Integer restaurantId) {
        return save(dish, restaurantId);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)})
    public void update(Dish updated, Integer updatedId, Integer restaurantId) {
        final var dish = dishRepository.checkBelong(updatedId, restaurantId);
        dish.setName(updated.getName());
        dish.setPrice(updated.getPrice());
    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)})
    public void delete(int id, int restaurantId) {
        dishRepository.delete(dishRepository.checkBelong(id, restaurantId));
    }

    @Transactional
    protected Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(dish);
    }
}
