package ru.gritandrey.restaurantvotingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
@AllArgsConstructor
@Setter
@Getter
public class Dish extends AbstractNamedEntity {
}
