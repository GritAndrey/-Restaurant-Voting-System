package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.exception.DataConflictException;
import ru.gritandrey.restaurantvotingsystem.model.MenuItem;

import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<MenuItem>, QuerydslPredicateExecutor<MenuItem> {

    @Query("select d from MenuItem d where d.id = :id and d.restaurant.id = :restaurantId")
    Optional<MenuItem> get(int id, int restaurantId);

    default MenuItem checkBelong(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new DataConflictException("MenuItem id=" + id + " doesn't belong to Restaurant id=" + restaurantId));
    }
}
