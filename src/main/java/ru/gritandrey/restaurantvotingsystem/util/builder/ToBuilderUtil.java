package ru.gritandrey.restaurantvotingsystem.util.builder;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.model.RestaurantDish;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantDishTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class ToBuilderUtil {

    public static RestaurantDishTo getRestaurantDishTo(RestaurantDish restaurantDish) {
        return RestaurantDishTo.builder()
                .restaurantId(restaurantDish.getRestaurant().getId())
                .date(restaurantDish.getDate())
                .name(restaurantDish.getDish().getName())
                .price(restaurantDish.getPrice())
                .id(restaurantDish.id())
                .build();
    }

    public static List<RestaurantDishTo> getRestaurantDishTos(Collection<RestaurantDish> restaurantDishes) {
        return restaurantDishes.stream().map(ToBuilderUtil::getRestaurantDishTo).collect(toList());
    }

    public static RestaurantWithMenuTo getRestaurantWithMenuTo(Restaurant restaurant) {
        return RestaurantWithMenuTo.builder()
                .menu(getRestaurantDishTos(restaurant.getMenu()))
                .address(restaurant.getAddress())
                .id(restaurant.id())
                .name(restaurant.getName())
                .build();
    }

    public static List<RestaurantWithMenuTo> getRestaurantWithMenuTos(Collection<Restaurant> restaurants) {
        return restaurants.stream().map(ToBuilderUtil::getRestaurantWithMenuTo).collect(toList());
    }
}
