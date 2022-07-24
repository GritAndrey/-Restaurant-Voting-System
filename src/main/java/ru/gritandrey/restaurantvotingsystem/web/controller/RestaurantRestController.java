package ru.gritandrey.restaurantvotingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    public static final String REST_URL = "/api/rest/restaurants";
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        final var restaurant = service.get(id);
        log.info("Get Restaurant with id {} without menu: {}", restaurant.getId(), restaurant);
        return restaurant;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        final var restaurants = service.getAll();
        log.info("GetAll Restaurants without menu: {}", restaurants);
        return restaurants;
    }

    @GetMapping("/{id}/menu")
    public RestaurantWithMenuTo getWithMenu(@PathVariable int id) {
        final RestaurantWithMenuTo restaurant = service.getWithMenu(id);
        log.info("Get Restaurant with id {} with menu on today: {}", restaurant.getId(), restaurant);
        return restaurant;
    }

    @GetMapping("/menu")
    public List<RestaurantWithMenuTo> getAllWithMenu() {
        final List<RestaurantWithMenuTo> restaurants = service.getAllWithMenu();
        log.info("GetAll Restaurants with menu on today: {}", restaurants);
        return restaurants;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> createWithLocation(@RequestBody RestaurantTo restaurantTo) {
        // TODO: 24.07.2022 Check new
        final var created = service.create(restaurantTo);
        log.info("Create {}", created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(RestaurantMapper.getTo(created));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("Update restaurant: {} with id: {}", restaurantTo, id);
        // TODO: 24.07.2022 assureIdConsistent
        service.update(restaurantTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete {}", id);
        service.delete(id);
    }
}
