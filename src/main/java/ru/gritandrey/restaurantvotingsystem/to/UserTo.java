package ru.gritandrey.restaurantvotingsystem.to;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.gritandrey.restaurantvotingsystem.model.AbstractNamedEntity;
import ru.gritandrey.restaurantvotingsystem.model.HasId;
import ru.gritandrey.restaurantvotingsystem.util.validation.NoHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserTo extends AbstractNamedEntity implements HasId {

    @Email
    @NotBlank
    @Size(max = 128)
    @NoHtml  // https://stackoverflow.com/questions/17480809
    String email;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;

    @Builder
    public UserTo(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo:" + id + '[' + email + ']';
    }
}
