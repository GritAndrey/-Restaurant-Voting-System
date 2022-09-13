package ru.gritandrey.restaurantvotingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.gritandrey.restaurantvotingsystem.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends NamedEntity {
    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 2, max = 128)
    @NoHtml
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonBackReference
    @ToString.Exclude
    private List<MenuItem> menu;

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }
}
