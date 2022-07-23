package ru.gritandrey.restaurantvotingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantDishTo;
import ru.gritandrey.restaurantvotingsystem.util.builder.ToBuilderUtil;

import java.util.Comparator;
import java.util.stream.Collectors;

import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@SpringBootTest
@Transactional
class RestaurantDishServiceTest {
    @Autowired
    private RestaurantDishService service;

    @Test
    void getAll() {
        final var all = service.getAll();
        RESTAURANT_DISH_TO_MATCHER.assertMatch(all, ToBuilderUtil.getRestaurantDishTos(restaurantDishes)
                .stream()
                .sorted(Comparator.comparing(RestaurantDishTo::getId))
                .collect(Collectors.toList()));
    }

    @Test
    void get() {
        final var restaurantDishTo = service.get(RESTAURANT_DISH1_ID);
        RESTAURANT_DISH_TO_MATCHER.assertMatch(restaurantDishTo, ToBuilderUtil.getRestaurantDishTo(restaurantDish1));
    }
}
