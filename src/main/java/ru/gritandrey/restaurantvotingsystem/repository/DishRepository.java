package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.exception.DataConflictException;
import ru.gritandrey.restaurantvotingsystem.model.Dish;

import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish>, FilterDishRepository {

    @Query("select d from Dish d where d.id = :id and d.restaurant.id = :restaurantId")
    Optional<Dish> get(int id, int restaurantId);

    default Dish checkBelong(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " doesn't belong to Restaurant id=" + restaurantId));
    }
}
