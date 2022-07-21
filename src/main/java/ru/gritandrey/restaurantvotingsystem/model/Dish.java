package ru.gritandrey.restaurantvotingsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {
    // :TODO: money https://stackoverflow.com/questions/8148684/what-data-type-to-use-for-money-in-java/43051227#43051227
    @Column(name = "price")
    private double price;

    protected Dish() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
