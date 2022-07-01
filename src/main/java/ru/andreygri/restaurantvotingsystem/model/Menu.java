package ru.andreygri.restaurantvotingsystem.model;

import java.util.Date;
import java.util.List;

public class Menu extends AbstractBaseEntity {
    List<Dish> dishes;
    private Restaurant restaurant;
    private Date date;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
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
}
