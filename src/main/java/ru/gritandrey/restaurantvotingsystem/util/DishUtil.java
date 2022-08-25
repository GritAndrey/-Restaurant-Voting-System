package ru.gritandrey.restaurantvotingsystem.util;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class DishUtil {

    public static DishTo getTo(Dish dish) {
        return DishTo.builder()
                .restaurantId(dish.getRestaurant() == null ? null : dish.getRestaurant().getId())
                .name(dish.getName())
                .date(dish.getDate())
                .price(dish.getPrice())
                .id(dish.getId())
                .build();
    }

    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream().map(DishUtil::getTo).collect(toList());
    }

    public static Dish getDish(DishTo dishTo) {
        return new Dish(dishTo.getId(),
                dishTo.getPrice(),
                dishTo.getName(),
                new Restaurant(),
                dishTo.getDate()
        );
    }

    public static List<Dish> getDishes(Collection<DishTo> dishesTo) {
        return dishesTo.stream().map(DishUtil::getDish).collect(toList());
    }
}
