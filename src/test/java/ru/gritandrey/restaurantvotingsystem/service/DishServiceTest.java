package ru.gritandrey.restaurantvotingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;

import java.util.Comparator;
import java.util.stream.Collectors;

import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@SpringBootTest
@Transactional
class DishServiceTest {
    @Autowired
    private DishService service;

    @Test
    void getAll() {
        final var all = service.getAll();
        DISH_TO_MATCHER.assertMatch(all, DishMapper.getTos(dishes)
                .stream()
                .sorted(Comparator.comparing(DishTo::getId))
                .collect(Collectors.toList()));
    }

    @Test
    void get() {
        final var restaurantDishTo = service.get(DISH1_ID);
        DISH_TO_MATCHER.assertMatch(restaurantDishTo, DishMapper.getTo(dish1));
    }
}
