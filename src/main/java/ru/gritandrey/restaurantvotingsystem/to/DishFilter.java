package ru.gritandrey.restaurantvotingsystem.to;

import java.time.LocalDate;

public record DishFilter(Integer restaurantId,
                         LocalDate startDate,
                         LocalDate endDate) {
}
