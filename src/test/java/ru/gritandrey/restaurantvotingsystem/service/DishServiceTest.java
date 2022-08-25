package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.exception.DataConflictException;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.util.DishUtil;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@DisplayName("Dish service crud test")
@RequiredArgsConstructor
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DishServiceTest {

    private final DishService dishService;

    @Test
    @DisplayName("Get one dish")
    void get() {
        final var dish = dishService.get(DISH1_ID, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(dish, dish1);
    }

    @Test
    @DisplayName("Get all today dishes")
    void getAllToday() {
        final var actual = dishService.getByFilter(new DishFilter(null, TODAY, null)).stream()
                .flatMap(menuTo -> menuTo.getDishes().stream())
                .sorted(comparing(DishTo::getId))
                .collect(toList());
        DISH_TO_MATCHER.assertMatch(actual, DishUtil.getTos(dishes));
    }

    @Test
    @DisplayName("Get Dish with fake id. Must be DataConflictException")
    void getNotFound() {
        assertThrows(DataConflictException.class, () -> dishService.get(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    @DisplayName("Checking new dish creation")
    void create() {
        final var newDish = getNewDishWithExistingNameAndRestaurant();
        final Dish created = dishService.create(newDish, newDish.getRestaurant().getId());
        final var newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId, RESTAURANT1_ID), newDish);
    }

    @Test
    @DisplayName("Update dish1")
    void update() {
        final Dish updatedDish = getUpdatedDish();
        dishService.update(updatedDish, updatedDish.getId(), updatedDish.getRestaurant().getId());
        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID, RESTAURANT1_ID), updatedDish);
    }

    @Test
    @DisplayName("Delete dish1")
    void delete() {
        dishService.delete(DISH1_ID, RESTAURANT1_ID);
        assertThrows(DataConflictException.class, () -> dishService.get(DISH1_ID, RESTAURANT1_ID));
    }
}
