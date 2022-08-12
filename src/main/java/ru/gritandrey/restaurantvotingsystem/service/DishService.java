package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.model.Food;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.FoodRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;

import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.findById(id), id);
    }

    public List<DishTo> getAll() {
        return DishMapper.getTos(dishRepository.findAll());
    }

    @Transactional
    public Dish create(DishTo dishTo) {
        Assert.notNull(dishTo, "Dish must not be null");
        return save(getDishWithFood(dishTo), dishTo.getRestaurantId());
    }

    @Transactional
    public void update(DishTo dishTo) {
        Assert.notNull(dishTo, "Dish must not be null");
        final var dish = getDishWithFood(dishTo);
        checkNotFoundWithId(save(dish, dishTo.getRestaurantId()), dish.id());
    }

    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id) != 0, id);
    }

    private Dish getDishWithFood(DishTo dishTo) {
        final var mayBeFood = foodRepository.findByName(dishTo.getName());
        Dish dish = DishMapper.getDish(dishTo);
        dish.setFood(mayBeFood.orElseGet(() -> foodRepository.save(new Food(dishTo.getName()))));
        return dish;
    }

    private Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.id()) == null) {
            return null;
        }
        dish.setRestaurant(checkNotFoundWithId(restaurantRepository.getReferenceById(restaurantId), restaurantId));
        return dishRepository.save(dish);
    }
}
