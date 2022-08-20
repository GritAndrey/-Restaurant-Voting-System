package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish>, FilterDishRepository {

    @Query("select d from Dish d where d.restaurant.id=:restaurantId")
    List<Dish> findAllByRestaurantId(int restaurantId);

}
