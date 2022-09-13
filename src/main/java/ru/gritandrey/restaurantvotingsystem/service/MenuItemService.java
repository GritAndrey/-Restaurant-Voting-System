package ru.gritandrey.restaurantvotingsystem.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.MenuItem;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.repository.querydsl.QPredicates;
import ru.gritandrey.restaurantvotingsystem.to.MenuItemFilter;
import ru.gritandrey.restaurantvotingsystem.to.MenuTo;
import ru.gritandrey.restaurantvotingsystem.util.MenuItemUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static ru.gritandrey.restaurantvotingsystem.model.QMenuItem.menuItem;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItem get(int dishId, int restaurantId) {
        return dishRepository.checkBelong(dishId, restaurantId);
    }

    @Cacheable(value = "menus",
            condition = ("#menuItemFilter.startDate != null && #menuItemFilter.startDate.equals(T(java.time.LocalDate).now())")
            , keyGenerator = "DishFilterTodayKeyGenerator")
    public List<MenuTo> getByFilter(MenuItemFilter menuItemFilter) {
        final Predicate predicate = getPredicate(menuItemFilter);
        final Iterable<MenuItem> filteredDishes = dishRepository.findAll(predicate);
        List<MenuItem> menuItems = new ArrayList<>();
        filteredDishes.forEach(menuItems::add);
        return getMenuTos(menuItems);
    }

    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", key = "#restaurantId")
    })
    public MenuItem create(MenuItem menuItem, Integer restaurantId) {
        return save(menuItem, restaurantId);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "menus", allEntries = true),
            @CacheEvict(value = "restWithMenu", key = "#restaurantId")})
    public void update(MenuItem updated, Integer updatedId, Integer restaurantId) {
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
    protected MenuItem save(MenuItem menuItem, int restaurantId) {
        menuItem.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(menuItem);
    }

    private Predicate getPredicate(MenuItemFilter menuItemFilter) {
        return QPredicates.builder()
                .add(menuItemFilter.restaurantId(), menuItem.restaurant.id::eq)
                .add(menuItemFilter.startDate(), menuItem.date::goe)
                .add(menuItemFilter.endDate(), menuItem.date::loe)
                .build();
    }

    private List<MenuTo> getMenuTos(List<MenuItem> filteredMenuItems) {
        record MenuKey(LocalDate menuDate,
                       Integer restaurantId) {
        }
        final Map<MenuKey, List<MenuItem>> groupedByDateAndRestaurantId = filteredMenuItems.stream()
                .collect(groupingBy(dish -> new MenuKey(dish.getDate(), dish.getRestaurant().getId())));
        return groupedByDateAndRestaurantId.entrySet().stream()
                .map(entry -> MenuTo.builder()
                        .restaurantId(entry.getKey().restaurantId())
                        .menuDate(entry.getKey().menuDate())
                        .dishes(MenuItemUtil.getTos(entry.getValue()))
                        .build()
                ).sorted(Comparator.comparing(MenuTo::getMenuDate).reversed().thenComparing(MenuTo::getRestaurantId)).collect(toList());
    }
}
