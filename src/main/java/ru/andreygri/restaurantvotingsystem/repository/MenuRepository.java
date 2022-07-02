package ru.andreygri.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.andreygri.restaurantvotingsystem.model.Dish;
import ru.andreygri.restaurantvotingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query("""
        SELECT menu FROM Menu menu
        JOIN FETCH menu.restaurant restaurant
        WHERE menu.date >= :startDate
        AND menu.date <= :endDate
        AND restaurant.id = :restaurantId
        """)
    List<Menu> getMenusOnDateByRestaurantId(@Param("startDate")LocalDate startDate,
                                            @Param("endDate")LocalDate endDate,
                                            @Param("restaurantId") Integer restaurantId);

}
