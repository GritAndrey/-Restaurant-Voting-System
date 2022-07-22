package ru.gritandrey.restaurantvotingsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends AbstractNamedEntity {
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<RestaurantDish>  menu;

    public Restaurant(Integer id, String name, String address) {
        super(id, name);
        this.address = address;
    }

}
