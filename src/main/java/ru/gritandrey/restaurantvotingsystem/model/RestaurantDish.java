package ru.gritandrey.restaurantvotingsystem.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "restaurant_dish")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDish extends AbstractBaseEntity {

    // :TODO: money https://stackoverflow.com/questions/8148684/what-data-type-to-use-for-money-in-java/43051227#43051227
    @Column(name = "price")
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_name_id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "dish_date")
    private LocalDate date;

}
