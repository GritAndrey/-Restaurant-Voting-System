package ru.gritandrey.restaurantvotingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish extends AbstractBaseEntity {

    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_name_id")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "dish_date")
    private LocalDate date;

    public Dish(Integer id, BigDecimal price, Food food, Restaurant restaurant, LocalDate date) {
        super(id);
        this.price = price;
        this.food = food;
        this.restaurant = restaurant;
        this.date = date;
    }

    public String toString() {
        return "Dish(price=" + this.getPrice() + ", name=" +
                this.getFood().getName() + ", restaurantId=" +
                this.getRestaurant().getId() + ", date=" +
                this.getDate() + ")";
    }
}
