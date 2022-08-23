package ru.gritandrey.restaurantvotingsystem.to;

import java.time.LocalDate;

public record VoteFilter(Integer restaurantId,
                         LocalDate startDate,
                         LocalDate endDate,
                         Integer userId) {
}
