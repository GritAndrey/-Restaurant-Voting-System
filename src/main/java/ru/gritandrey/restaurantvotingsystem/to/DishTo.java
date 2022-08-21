package ru.gritandrey.restaurantvotingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
@Jacksonized
@SuperBuilder
public class DishTo extends BaseTo {

    @Positive
    @NotNull
    BigDecimal price;
    @NotBlank
    @Size(min = 2, max = 128)
    String name;
    @NotNull
    Integer restaurantId;
}
