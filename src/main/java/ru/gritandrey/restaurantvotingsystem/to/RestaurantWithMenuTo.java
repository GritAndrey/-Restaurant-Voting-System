package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantWithMenuTo extends NamedTo {

    String address;
    @Builder.Default
    LocalDate menuDate = LocalDate.now();
    List<DishTo> menu;

    @Builder
    public RestaurantWithMenuTo(Integer id, String name, String address, List<DishTo> menu) {
        super(id, name);
        this.address = address;
        this.menu = menu;
    }
}
