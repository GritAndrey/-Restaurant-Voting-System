package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestaurantTo {
    Integer id;
    String name;
    String address;
}
