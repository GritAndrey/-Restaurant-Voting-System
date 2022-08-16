package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {

    @NotBlank
    @Size(min = 2, max = 128)
    String address;

    @Builder
    public RestaurantTo(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }
}
