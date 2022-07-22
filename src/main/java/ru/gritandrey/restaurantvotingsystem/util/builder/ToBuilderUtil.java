package ru.gritandrey.restaurantvotingsystem.util.builder;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.model.RestaurantDish;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantDishTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;

import static java.util.stream.Collectors.toSet;

@UtilityClass
public class ToBuilderUtil {

    public static RestaurantDishTo buildRestaurantDishTo(RestaurantDish restaurantDish) {
        return RestaurantDishTo.builder()
                .restaurantId(restaurantDish.getRestaurant().getId())
                .date(restaurantDish.getDate())
                .name(restaurantDish.getDish().getName())
                .price(restaurantDish.getPrice())
                .id(restaurantDish.id())
                .build();
    }

    public static RestaurantWithMenuTo buildRestaurantWithMenuTo(Restaurant restaurant) {
        return RestaurantWithMenuTo.builder()
                .menu(restaurant.getMenu().stream().map(ToBuilderUtil::buildRestaurantDishTo).collect(toSet()))
                .address(restaurant.getAddress())
                .id(restaurant.id())
                .name(restaurant.getName())
                .build();
    }
}
