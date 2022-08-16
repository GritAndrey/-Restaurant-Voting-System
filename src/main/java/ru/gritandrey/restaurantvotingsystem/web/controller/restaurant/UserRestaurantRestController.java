package ru.gritandrey.restaurantvotingsystem.web.controller.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.service.VoteService;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;
import ru.gritandrey.restaurantvotingsystem.util.SecurityUtil;
import ru.gritandrey.restaurantvotingsystem.web.controller.vote.UserVoteRestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class UserRestaurantRestController {

    public static final String REST_URL = "/api/rest/restaurants";
    private final RestaurantService restaurantService;
    private final VoteService voteService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        final var restaurant = restaurantService.get(id);
        log.info("Get Restaurant with id {} without menu: {}", restaurant.getId(), restaurant);
        return restaurant;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        final var restaurants = restaurantService.getAll();
        log.info("GetAll Restaurants without menu: {}", restaurants);
        return restaurants;
    }

    @GetMapping("/{id}/menu")
    public RestaurantWithMenuTo getWithMenu(@PathVariable int id) {
        final RestaurantWithMenuTo restaurant = restaurantService.getWithMenu(id);
        log.info("Get Restaurant with id {} with menu on today: {}", restaurant.getId(), restaurant);
        return restaurant;
    }

    @GetMapping("/menu")
    public List<RestaurantWithMenuTo> getAllWithMenu() {
        final List<RestaurantWithMenuTo> restaurants = restaurantService.getAllWithMenu();
        log.info("GetAll Restaurants with menu on today: {}", restaurants);
        return restaurants;
    }

    @PostMapping(value = "{restaurantId}/vote")
    public ResponseEntity<VoteTo> voteForRestaurant(@PathVariable int restaurantId) {
        final var userId = SecurityUtil.authId();
        log.info("Create Vote.\nuserId: {}\nrestaurantId {}", userId, restaurantId);
        final var created = voteService.create(restaurantId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(UserVoteRestController.REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource)
                .body(created);
    }
}
