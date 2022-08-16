package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Transactional
    @Query("delete from Restaurant r where r.id=:id")
    int delete(@Param("id") int id);

    @Query("select r from Restaurant r join fetch r.menu m join fetch m.food where r.id=:id and m.date=:date")
    Restaurant getRestaurantByIdWithMenu(@Param("id") int id, @Param("date") LocalDate date);

    @Query("select distinct r from Restaurant r join fetch r.menu m join fetch m.food where m.date=:date")
    List<Restaurant> findAllWithMenus(LocalDate date);
}
