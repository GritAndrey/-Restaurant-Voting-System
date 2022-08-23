package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.exception.IllegalRequestDataException;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.to.MenuTo;
import ru.gritandrey.restaurantvotingsystem.util.DishUtil;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public Dish get(int id) {
        return dishRepository.getExisted(id);
    }

    @Cacheable(value = "menus", condition = ("#dishFilter.startDate != null && #dishFilter.startDate.equals(T(java.time.LocalDate).now())"))
    public List<MenuTo> getByFilter(DishFilter dishFilter) {
        final var filteredDishes = dishRepository.findAllByFilter(dishFilter);
        if (filteredDishes.isEmpty()) {
            throw new IllegalRequestDataException("No dishes with filter:" + dishFilter);
        }
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
                )
                .collect(toList());
    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)
    })
    public Dish create(DishTo dishTo) {
        return save(DishUtil.getDish(dishTo), dishTo.getRestaurantId());
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)})
    public void update(DishTo dishTo) {
        final var dish = get(dishTo.getId());
        dish.setRestaurant(restaurantRepository.getExisted(dishTo.getRestaurantId()));
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", allEntries = true)})
    public void delete(int id) {
        dishRepository.deleteExisted(id);
    }

    @Transactional
    protected Dish save(Dish dish, int restaurantId) {
        dish.setDate(LocalDate.now());
        dish.setRestaurant(restaurantRepository.getExisted(restaurantId));
        return dishRepository.save(dish);
    }
}
