package ru.gritandrey.restaurantvotingsystem.web.controller.dish;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gritandrey.restaurantvotingsystem.service.DishService;
import ru.gritandrey.restaurantvotingsystem.to.DishFilter;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.to.MenuTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = UserDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tags({@Tag(name = "User Dish controller", description = "Get dishes by restaurantId and(or) date")})
public class UserDishRestController {
    public static final String REST_URL = "/api/rest/dishes";
    private final DishService dishService;

    @GetMapping
    @Operation(summary = "GetAll dishes by Restaurant Id and period.")
    public List<MenuTo> getAllBy(@RequestParam(required = false) Integer restaurantId,
                                 @RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        if (startDate == null && endDate == null && restaurantId == null) {
            startDate = LocalDate.now();
        }
        final var menus = dishService.getByFilter(new DishFilter(restaurantId, startDate, endDate));
        log.info("GetAll dishes by Restaurant Id and Date  {}", menus);
        menus.sort(Comparator.comparing(MenuTo::getMenuDate).reversed().thenComparing(MenuTo::getRestaurantId));
        return menus;
    }

    @GetMapping("{id}")
    public DishTo get(@PathVariable int id) {
        DishTo dish = DishMapper.getTo(dishService.get(id));
        log.info("Get restaurant dish with id: {} {}", dish.getId(), dish);
        return dish;
    }
}
