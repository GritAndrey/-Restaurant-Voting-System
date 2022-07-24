package ru.gritandrey.restaurantvotingsystem.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "food")
@NoArgsConstructor
public class Food extends AbstractNamedEntity {
    public Food(Integer id, String name) {
        super(id, name);
    }

    public Food(String dishName) {
        this.name = dishName;
    }
}
