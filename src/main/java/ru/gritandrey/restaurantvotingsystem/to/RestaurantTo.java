package ru.gritandrey.restaurantvotingsystem.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import ru.gritandrey.restaurantvotingsystem.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Value
@ToString(callSuper = true)
@Jacksonized
@SuperBuilder
public class RestaurantTo extends NamedTo {
    @NotBlank
    @Size(min = 2, max = 128)
    @NoHtml
    String address;

    @Builder.Default
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonDeserialize
    List<MenuItemTo> menu = new ArrayList<>();
}
