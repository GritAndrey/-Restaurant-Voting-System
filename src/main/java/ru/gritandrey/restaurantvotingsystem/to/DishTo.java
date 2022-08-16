package ru.gritandrey.restaurantvotingsystem.to;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
public class DishTo extends BaseTo {

    @Positive
    @NotNull
    BigDecimal price;
    @NotBlank
    @Size(min = 2, max = 128)
    String name;
    @NotNull
    Integer restaurantId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    LocalDate date;

    @Builder
    public DishTo(Integer id, BigDecimal price, String name, Integer restaurantId, LocalDate date) {
        super(id);
        this.price = price;
        this.name = name;
        this.restaurantId = restaurantId;
        this.date = date;
    }
}
