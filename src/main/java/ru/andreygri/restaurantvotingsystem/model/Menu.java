package ru.andreygri.restaurantvotingsystem.model;

import java.util.Date;
import java.util.Set;

public class Menu extends AbstractBaseEntity {
    private Date date;
    private Restaurant restaurant;
    private Set<Dish> dishes;
    private Set<Vote> votes;
}
