package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;
import ru.gritandrey.restaurantvotingsystem.model.HasId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
public class DishTo implements HasId {

    Integer id;
    @Positive
    BigDecimal price;
    @NotBlank
    @Size(min = 2, max = 128)
    String name;
    @NotNull
    Integer restaurantId;
    @NotNull
    LocalDate date;

    @Override
    public void setId(Integer id) {
    }
}
