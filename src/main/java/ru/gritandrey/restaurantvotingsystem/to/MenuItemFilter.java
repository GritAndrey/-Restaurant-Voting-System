package ru.gritandrey.restaurantvotingsystem.to;

import java.time.LocalDate;

public record MenuItemFilter(Integer restaurantId,
                             LocalDate startDate,
                             LocalDate endDate) {
}
