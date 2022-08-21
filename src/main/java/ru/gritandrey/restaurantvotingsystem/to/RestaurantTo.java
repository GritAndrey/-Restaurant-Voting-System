package ru.gritandrey.restaurantvotingsystem.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {

    @NotBlank
    @Size(min = 2, max = 128)
    String address;

    @Builder.Default
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<DishTo> menu = new ArrayList<>();

    @Builder
    public RestaurantTo(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }
}
