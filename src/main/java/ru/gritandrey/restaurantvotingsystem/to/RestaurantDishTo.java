package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class RestaurantDishTo {
    Integer id;
    BigDecimal price;
    String name;
    Integer restaurantId;
    LocalDate date;
}
