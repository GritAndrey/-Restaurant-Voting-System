package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    Integer restaurantId;
    Integer userId;
    LocalDate date;
    LocalTime time;

    @Builder
    public VoteTo(Integer id, Integer restaurantId, Integer userId, LocalDate date, LocalTime time) {
        super(id);
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }
}
