package ru.gritandrey.restaurantvotingsystem.web.controller.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;

import java.util.Optional;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
@Tags({@Tag(name = "User restaurant controller", description = "View available restaurants. With or without menu.")})
public class UserRestaurantController {

    public static final String REST_URL = "/api/restaurants";
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_ITEMS_PER_PAGE = 10;
    private final RestaurantService restaurantService;

    @GetMapping("/{id}")
    @Operation(summary = "return restaurant by id. If showMenu=true, returns restaurant with menu on today")
    public RestaurantTo get(@PathVariable int id, @RequestParam(defaultValue = "false") boolean showMenu) {
        if (showMenu) {
            log.info("Get Restaurant with id {} with menu", id);
            return restaurantService.getWithMenu(id);
        }
        log.info("Get Restaurant with id {} without menu.", id);
        return restaurantService.get(id);
    }

    @GetMapping()
    @Operation(summary = "return all restaurants. If showMenu=true, returns restaurants with menu on today")
    public Page<RestaurantTo> getAll(@RequestParam(defaultValue = "false") boolean showMenu,
                                     @RequestParam(required = false) Integer page,
                                     @RequestParam(required = false) Integer itemsPerPage) {
        page = Optional.ofNullable(page).orElse(DEFAULT_PAGE);
        itemsPerPage = Optional.ofNullable(itemsPerPage).orElse(DEFAULT_ITEMS_PER_PAGE);
        if (showMenu) {
            log.info("GetAll Restaurants with menu on today");
            return restaurantService.getAllWithMenu(page, itemsPerPage);
        }
        log.info("GetAll Restaurants without menu");
        return restaurantService.getAll(page, itemsPerPage);
    }
}
