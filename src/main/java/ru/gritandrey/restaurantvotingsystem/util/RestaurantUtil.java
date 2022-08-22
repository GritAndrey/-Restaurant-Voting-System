package ru.gritandrey.restaurantvotingsystem.util;

import lombok.experimental.UtilityClass;
import org.hibernate.Hibernate;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantTo getTo(Restaurant restaurant) {

        final var restaurantTo = RestaurantTo.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();
        if (Hibernate.isInitialized(restaurant.getMenu()) && restaurant.getMenu() != null) {
            restaurantTo.getMenu().addAll(DishUtil.getTos(restaurant.getMenu()));
        }
        return restaurantTo;
    }

    public static Restaurant getRestaurant(RestaurantTo restaurantTo) {
        final var restaurant = new Restaurant(restaurantTo.getId(), restaurantTo.getName(), restaurantTo.getAddress());
        if (!restaurantTo.getMenu().isEmpty()) {
            restaurant.setMenu(DishUtil.getDishes(restaurantTo.getMenu()));
        }
        return restaurant;
    }
}
