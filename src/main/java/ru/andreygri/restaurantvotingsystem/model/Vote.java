package ru.andreygri.restaurantvotingsystem.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vote extends AbstractBaseEntity {
    private User user;
    private Restaurant restaurant;
    private LocalDate date;
    private LocalTime time;

    public Vote() {
    }

    public Vote(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
