package ru.gritandrey.restaurantvotingsystem.util;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
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
}
