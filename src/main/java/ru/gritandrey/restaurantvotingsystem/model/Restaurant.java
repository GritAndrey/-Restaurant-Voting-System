package ru.gritandrey.restaurantvotingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {
    @Column(name = "address")
    @NotBlank
    @Size(min = 2, max = 128)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonBackReference
    @ToString.Exclude
    private List<Dish> menu;

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }
}
