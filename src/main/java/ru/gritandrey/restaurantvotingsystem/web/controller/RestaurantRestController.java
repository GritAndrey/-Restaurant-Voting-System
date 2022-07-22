package ru.gritandrey.restaurantvotingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        final var restaurants = service.getAll();
        log.info("GetAll Restaurants without menu: {}", restaurants);
        return restaurants;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        final var restaurant = service.get(id);
        log.info("Get Restaurant with id {} without menu: {}", restaurant.getId(), restaurant);
        return restaurant;
    }
    @GetMapping("/{id}/menu")
    public RestaurantWithMenuTo getWithMenu(@PathVariable int id) {
        final RestaurantWithMenuTo restaurant = service.getWithMenu(id);
        log.info("Get Restaurant with id {} with menu on today: {}", restaurant.getId(), restaurant);
        return restaurant;
    }

}
