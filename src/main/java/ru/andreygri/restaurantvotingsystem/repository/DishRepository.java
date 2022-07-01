package ru.andreygri.restaurantvotingsystem.repository;

import ru.andreygri.restaurantvotingsystem.model.Dish;

import java.util.List;

public interface DishRepository {
    // null if updated meal does not belong to userId
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll();

}
