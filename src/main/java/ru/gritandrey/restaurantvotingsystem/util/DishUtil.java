package ru.gritandrey.restaurantvotingsystem.util;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.to.DishCreateTo;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class DishUtil {

    public static DishTo getTo(Dish dish) {
        return DishTo.builder()
                .name(dish.getName())
                .price(dish.getPrice())
                .id(dish.getId())
                .build();
    }

    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream().map(DishUtil::getTo).collect(toList());
    }

    public static DishCreateTo getCreateTo(Dish dish) {
        return DishCreateTo.builder()
                .restaurantId(dish.getRestaurant() == null ? null : dish.getRestaurant().getId())
                .name(dish.getName())
                .date(dish.getDate())
                .price(dish.getPrice())
                .id(dish.getId())
                .build();
    }

    public static Dish getDish(DishCreateTo dishCreateTo) {
        return new Dish(dishCreateTo.getId(),
                dishCreateTo.getPrice(),
                dishCreateTo.getName(),
                new Restaurant(),
                dishCreateTo.getDate()
        );
    }
}
