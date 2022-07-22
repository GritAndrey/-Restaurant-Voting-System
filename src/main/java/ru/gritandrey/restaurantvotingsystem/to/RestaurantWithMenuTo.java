package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class RestaurantWithMenuTo {
    Integer id;
    String name;
    String address;
    Set<RestaurantDishTo> menu;

}
