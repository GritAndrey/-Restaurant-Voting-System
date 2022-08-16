package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;
import ru.gritandrey.restaurantvotingsystem.model.HasId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class DishTo implements HasId {
    Integer id;
    BigDecimal price;
    String name;
    Integer restaurantId;
    LocalDate date;

    @Override
    public void setId(Integer id) {}
}
