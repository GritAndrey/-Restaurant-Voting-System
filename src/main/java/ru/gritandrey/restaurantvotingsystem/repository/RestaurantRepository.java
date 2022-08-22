package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    Page<Restaurant> findAllBy(Pageable pageable);

    @Query("select r from Restaurant r join fetch r.menu m where r.id=:id and m.date=:date")
    Optional<Restaurant> getRestaurantByIdWithMenu(int id, LocalDate date);

    @Query(value = "select distinct r from Restaurant r join fetch r.menu m where m.date=:date",
            countQuery = "select count(distinct r.id) from Restaurant r")
    Page<Restaurant> findAllWithMenus(Pageable pageable, LocalDate date);
}
