package ru.gritandrey.restaurantvotingsystem.service;

import org.springframework.stereotype.Service;
import ru.gritandrey.restaurantvotingsystem.repository.DishRepository;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantDishRepository;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantDishTo;
import ru.gritandrey.restaurantvotingsystem.util.builder.ToBuilderUtil;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RestaurantDishService {
    private final RestaurantDishRepository restaurantDishRepository;
    private final DishRepository dishRepository;

    public RestaurantDishService(RestaurantDishRepository restaurantDishRepository, DishRepository dishRepository) {
        this.restaurantDishRepository = restaurantDishRepository;
        this.dishRepository = dishRepository;
    }

    public List<RestaurantDishTo> getAll() {
        return restaurantDishRepository.findAll().stream()
                .map(ToBuilderUtil::buildRestaurantDishTo).collect(toList());
    }

    public RestaurantDishTo findById(int id) {
        final var restaurantDish = restaurantDishRepository.findById(id);
        return restaurantDish.map(ToBuilderUtil::buildRestaurantDishTo).orElse(null);
    }
}
