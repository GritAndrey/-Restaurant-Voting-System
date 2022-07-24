package ru.gritandrey.restaurantvotingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@SpringBootTest
@Transactional
class RestaurantServiceTest {
    @Autowired
    private RestaurantService service;

    @Test
    void getAll() {
        RESTAURANT_MATCHER.assertMatch(service.getAll(), restaurants);
    }

    @Test
    void get() {
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT1_ID), restaurant1);
    }

    @Test
    void getWithMenu() {
        RESTAURANT_TO_MATCHER.assertMatch((service.getWithMenu(RESTAURANT1_ID)), restaurantWithMenuTo);
    }

    @Test
    void getNotFound() {
        assertThrows(IllegalArgumentException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void getAllWithMenus() {
        RESTAURANT_TO_MATCHER.assertMatch(service.getAllWithMenus(), restaurantsTo);
    }
}
