package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import ru.gritandrey.restaurantvotingsystem.util.validation.NoHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@Jacksonized
@SuperBuilder
public class NamedTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    @NoHtml
    protected String name;

    @Override
    public String toString() {
        return super.toString() + '[' + name + ']';
    }
}
