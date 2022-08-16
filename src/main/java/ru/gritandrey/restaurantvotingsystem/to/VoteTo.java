package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Data;
import ru.gritandrey.restaurantvotingsystem.model.HasId;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class VoteTo implements HasId {

    private Integer id;
    private Integer restaurantId;
    private Integer userId;
    private LocalDate date;
    private LocalTime time;
}
