package ru.gritandrey.restaurantvotingsystem.util.mapper;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class RestaurantMapper {

    public static RestaurantWithMenuTo getWithMenuTo(Restaurant restaurant) {
        return RestaurantWithMenuTo.builder()
                .id(restaurant.id())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .menu(DishMapper.getTos(restaurant.getMenu()))
                .build();
    }

    public static List<RestaurantWithMenuTo> getWithMenuTos(Collection<Restaurant> restaurants) {
        return restaurants.stream().map(RestaurantMapper::getWithMenuTo).collect(toList());
    }

    public static RestaurantTo getTo(Restaurant restaurant) {
        return RestaurantTo.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .build();
    }

    public static Restaurant getRestaurant(RestaurantTo restaurantTo) {
        return new Restaurant(restaurantTo.getId(), restaurantTo.getName(), restaurantTo.getAddress());
    }
}
