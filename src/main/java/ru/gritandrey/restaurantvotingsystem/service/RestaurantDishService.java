package ru.gritandrey.restaurantvotingsystem.service;

import org.springframework.stereotype.Service;
import ru.gritandrey.restaurantvotingsystem.model.RestaurantDish;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantDishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantDishTo;
import ru.gritandrey.restaurantvotingsystem.util.builder.ToBuilderUtil;

import java.util.List;
import java.util.Set;

import static ru.gritandrey.restaurantvotingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantDishService {
    private final RestaurantDishRepository restaurantDishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public RestaurantDishService(RestaurantDishRepository restaurantDishRepository, RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantDishRepository = restaurantDishRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    public List<RestaurantDishTo> getAll() {
        return ToBuilderUtil.getRestaurantDishTos(restaurantDishRepository.findAll());

    }

    public RestaurantDishTo get(int id) {
        return restaurantDishRepository.findById(id).map(ToBuilderUtil::getRestaurantDishTo).orElse(null);
    }

    private RestaurantDish save(RestaurantDish restaurantDish, int restaurantId) {
        if (!restaurantDish.isNew() && get(restaurantDish.id()) == null) {
            return null;
        }
        restaurantDish.setRestaurant(checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId));
        return restaurantDishRepository.save(restaurantDish);
    }
}
