package ru.gritandrey.restaurantvotingsystem.repository;

import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;

import java.util.List;

public interface FilterDishRepository {

    List<Dish> findAllByFilter(DishFilter filter);
}
