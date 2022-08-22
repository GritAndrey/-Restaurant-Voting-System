package ru.gritandrey.restaurantvotingsystem.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.repository.querydsl.QPredicates;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.model.QDish.dish;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilterDishRepositoryImpl implements FilterDishRepository {
    private final EntityManager entityManager;

    @Override
    public List<Dish> findAllByFilter(DishFilter filter) {
        final var predicate = QPredicates.builder()
                .add(filter.restaurantId(), dish.restaurant.id::eq)
                .add(filter.startDate(), dish.date::goe)
                .add(filter.endDate(), dish.date::loe)
                .build();
        return new JPAQuery<Dish>(entityManager)
                .select(dish)
                .from(dish)
                .where(predicate)
                .fetch();
    }
}
