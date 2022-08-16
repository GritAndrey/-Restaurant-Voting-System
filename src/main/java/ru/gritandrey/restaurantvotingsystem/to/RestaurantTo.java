package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.Value;
import ru.gritandrey.restaurantvotingsystem.model.HasId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
public class RestaurantTo implements HasId {

    Integer id;
    @NotBlank
    @Size(min = 2, max = 128)
    String name;
    @NotBlank
    @Size(min = 2, max = 128)
    String address;

    @Override
    public void setId(Integer id) {
    }
}
