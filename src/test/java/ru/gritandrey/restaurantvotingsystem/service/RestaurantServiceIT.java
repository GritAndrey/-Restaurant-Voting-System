package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@SpringBootTest
@DisplayName("Restaurant service tests")
@Transactional
@RequiredArgsConstructor
@ActiveProfiles("test")
class RestaurantServiceIT {

    private final RestaurantService service;

    @Test
    @DisplayName("Get restaurant without menu")
    void get() {
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), restaurant1);
    }

    @Test
    @DisplayName("Get all restaurants without menus")
    void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), restaurants);
    }

    @Test
    @DisplayName("Get restaurant with fake id. Must be IllegalArgumentException")
    void getNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    @DisplayName("Get restaurant with today`s menu")
    void getWithMenu() {
        RESTAURANT_TO_MATCHER.assertMatch((service.getWithMenu(RESTAURANT1_ID)), restaurantWithMenuTo);
    }

    @Test
    @DisplayName("Get all restaurants with today`s menu")
    void getAllWithMenus() {
        RESTAURANT_TO_MATCHER.assertMatch(service.getAllWithMenu(), restaurantsTo);
    }

    @Test
    @DisplayName("Create new restaurant")
    void create() {
        final var newRestaurant = getNewRestaurant();
        final var created = service.create(RestaurantMapper.getTo(newRestaurant));
        newRestaurant.setId(created.getId());
        RESTAURANT_MATCHER.assertMatch(newRestaurant, created);
    }

    @Test
    @DisplayName("Update restaurant1")
    void update() {
        final var updatedRestaurant = getUpdatedRestaurant();
        service.update(RestaurantMapper.getTo(updatedRestaurant));
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), updatedRestaurant);
    }

    @Test
    @DisplayName("Delete restaurant")
    void delete() {
        service.delete(RESTAURANT1_ID);
        assertThrows(IllegalArgumentException.class, () -> service.get(RESTAURANT1_ID));
    }
}
