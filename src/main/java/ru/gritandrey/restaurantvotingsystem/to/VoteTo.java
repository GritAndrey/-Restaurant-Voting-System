package ru.gritandrey.restaurantvotingsystem.to;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = true)
@Jacksonized
@SuperBuilder
public class VoteTo extends BaseTo {

    Integer restaurantId;
    Integer userId;
    LocalDate date;
    LocalTime time;
}
