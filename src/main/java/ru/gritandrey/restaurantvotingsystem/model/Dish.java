package ru.gritandrey.restaurantvotingsystem.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Dish extends AbstractNamedEntity {

}
