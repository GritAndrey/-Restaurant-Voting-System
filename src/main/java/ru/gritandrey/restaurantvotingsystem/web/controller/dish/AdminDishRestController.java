package ru.gritandrey.restaurantvotingsystem.web.controller.dish;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gritandrey.restaurantvotingsystem.service.DishService;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;
import ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class AdminDishRestController {
    public static final String REST_URL = "/api/rest/admin/dishes";
    private final DishService dishService;

    @GetMapping
    public List<DishTo> getAll() {
        final var restaurantDishes = dishService.getAll();
        log.info("GetAll restaurant dish  {}", restaurantDishes);
        return restaurantDishes;
    }

    @GetMapping("{id}")
    public DishTo get(@PathVariable int id) {
        DishTo dish = DishMapper.getTo(dishService.get(id));
        log.info("Get restaurant dish with id: {} {}", dish.getId(), dish);
        return dish;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishTo> createWithLocation(@RequestBody DishTo dishTo) {
        ValidationUtil.checkNew(dishTo);
        final var created = dishService.create(dishTo);
        log.info("Create {}", created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(DishMapper.getTo(created));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody DishTo dishTo, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(dishTo, id);
        log.info("Update {}", dishTo);
        dishService.update(dishTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete {}", id);
        dishService.delete(id);
    }
}
