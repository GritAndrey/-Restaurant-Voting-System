package ru.gritandrey.restaurantvotingsystem.util.mapper;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class DishMapper {
    public static DishTo getTo(Dish dish) {
        return DishTo.builder()
                .restaurantId(dish.getRestaurant().getId())
                .date(dish.getDate())
                .name(dish.getFood().getName())
                .price(dish.getPrice())
                .id(dish.id())
                .build();
    }

    public static List<DishTo> getTos(Collection<Dish> dishes) {
        return dishes.stream().map(DishMapper::getTo).collect(toList());
    }
}
