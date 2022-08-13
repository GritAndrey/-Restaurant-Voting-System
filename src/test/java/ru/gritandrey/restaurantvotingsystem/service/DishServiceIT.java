package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;

import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@DisplayName("Dish service crud test")
@RequiredArgsConstructor
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DishServiceIT {

    private final DishService dishService;

    @Test
    @DisplayName("Get one dish")
    void get() {
        final var dish = dishService.get(DISH1_ID);
        DISH_MATCHER.assertMatch(dish, dish1);
    }

    @Test
    @DisplayName("Get all dishes")
    void getAll() {
        final var all = dishService.getAll();
        DISH_TO_MATCHER.assertMatch(all, DishMapper.getTos(dishes)
                .stream()
                .sorted(Comparator.comparing(DishTo::getId))
                .collect(Collectors.toList()));
    }

    @Test
    @DisplayName("Get Dish with fake id. Must be IllegalArgumentException")
    void getNotFound() {
        assertThrows(IllegalArgumentException.class, () -> dishService.get(NOT_FOUND));
    }

    @Test
    @DisplayName("Checking new dish creation")
    void create() {
        final var newDish = getNewDishWithExistingNameAndRestaurant();
        final Dish created = dishService.create(DishMapper.getTo(newDish));
        final var newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId), newDish);
    }

    @Test
    @DisplayName("Checking that a new entry is NOT created in the food table when creating a dish with existing name")
    void createNewWithExistingName() {
        final Dish created = dishService.create(DishMapper.getTo(getNewDishWithExistingNameAndRestaurant()));
        Assertions.assertThat(created.getFood().getId()).isEqualTo(food10.id());
    }

    @Test
    @DisplayName("Checking that a new entry is created in the food table when creating a dish with a new name")
    void createNewWithNewName() {
        final Dish created = dishService.create(DishMapper.getTo(getNewDishWithNewNameAndRestaurant()));
        Assertions.assertThat(created.getFood().getId()).isEqualTo(created.getId() - 1);
    }

    @Test
    @DisplayName("Update dish1")
    void update() {
        final Dish updatedDish = getUpdatedDish();
        dishService.update(DishMapper.getTo(updatedDish));
        DISH_MATCHER.assertMatch(dishService.get(DISH1_ID), updatedDish);
    }

    @Test
    @DisplayName("Delete dish1")
    void delete() {
        dishService.delete(DISH1_ID);
        assertThrows(IllegalArgumentException.class, () -> dishService.get(DISH1_ID));
    }
}
