package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RestaurantWithMenuTo {
    Integer id;
    String name;
    String address;
    List<DishTo>  menu;
}
