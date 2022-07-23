package ru.gritandrey.restaurantvotingsystem.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
@NoArgsConstructor
public class Dish extends AbstractNamedEntity {
    public Dish(Integer id, String name) {
        super(id, name);
    }
}
