package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(of = {"menuDate", "restaurantId"})
@Value
@ToString
public class MenuTo {

    LocalDate menuDate;
    Integer restaurantId;
    List<DishTo> dishes;

    @Builder
    public MenuTo(LocalDate menuDate, Integer restaurantId, List<DishTo> dishes) {
        this.menuDate = menuDate;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
    }
}
