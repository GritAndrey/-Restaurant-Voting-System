package ru.andreygri.restaurantvotingsystem.model;

import java.util.Date;
import java.util.Set;

public class Menu extends AbstractBaseEntity {
    private Set<Dish> dishes;
    private Restaurant restaurant;
    private Date date;

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dishes=" + dishes +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }
}
