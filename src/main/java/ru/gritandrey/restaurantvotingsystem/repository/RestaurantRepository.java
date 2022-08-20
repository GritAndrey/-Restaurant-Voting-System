package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    Page<Restaurant> findAllBy(Pageable pageable);

    @Query("select r from Restaurant r join fetch r.menu m join fetch m.food where r.id=:id and m.date=:date")
    Restaurant getRestaurantByIdWithMenu(@Param("id") int id, @Param("date") LocalDate date);

    @Query("select distinct r from Restaurant r join fetch r.menu m join fetch m.food where m.date=:date")
    List<Restaurant> findAllWithMenus(LocalDate date);
}
