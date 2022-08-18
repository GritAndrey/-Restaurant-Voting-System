package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer>, FilterDishRepository {
    @Modifying
    @Transactional
    @Query("delete from Dish d where d.id=:id")
    int delete(@Param("id") int id);

    @Query("select d from Dish d where d.restaurant.id=:restaurantId")
    List<Dish> findAllByRestaurantId(int restaurantId);

}
