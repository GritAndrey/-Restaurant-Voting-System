package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish>, FilterDishRepository {
}
