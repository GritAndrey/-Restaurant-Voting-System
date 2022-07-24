package ru.gritandrey.restaurantvotingsystem.service;

import org.springframework.stereotype.Service;
import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.repository.FoodRepository;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;

import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    public DishService(DishRepository dishRepository, RestaurantRepository restaurantRepository, FoodRepository foodRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
    }

    public List<DishTo> getAll() {
        return DishMapper.getTos(dishRepository.findAll());

    }

    public DishTo get(int id) {
        return dishRepository.findById(id).map(DishMapper::getTo).orElse(null);
    }

    private Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.id()) == null) {
            return null;
        }
        dish.setRestaurant(checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId));
        return dishRepository.save(dish);
    }
}
