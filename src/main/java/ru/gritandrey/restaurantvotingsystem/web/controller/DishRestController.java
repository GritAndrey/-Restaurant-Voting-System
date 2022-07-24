package ru.gritandrey.restaurantvotingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gritandrey.restaurantvotingsystem.service.DishService;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final DishService service;

    public DishRestController(DishService service) {
        this.service = service;
    }

    @GetMapping
    public List<DishTo> getAll() {
        final var restaurantDishes = service.getAll();
        log.info("GetAll restaurant dish  {}", restaurantDishes);
        return restaurantDishes;
    }

    @GetMapping("{id}")
    public DishTo get(@PathVariable int id) {
        DishTo dish = service.get(id);
        log.info("Get restaurant dish with id: {} {}", dish.getId(), dish);
        return dish;
    }
}
