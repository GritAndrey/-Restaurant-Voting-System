package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Food;

import java.util.Optional;

@Transactional(readOnly = true)
public interface FoodRepository extends BaseRepository<Food> {

    Optional<Food> findByName(String name);
}
