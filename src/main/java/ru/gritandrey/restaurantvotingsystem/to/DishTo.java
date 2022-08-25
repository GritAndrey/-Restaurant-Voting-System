package ru.gritandrey.restaurantvotingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
@Jacksonized
@SuperBuilder
public class DishTo extends BaseTo {

    BigDecimal price;

    String name;
}
