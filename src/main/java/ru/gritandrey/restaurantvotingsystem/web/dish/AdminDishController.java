package ru.gritandrey.restaurantvotingsystem.web.dish;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gritandrey.restaurantvotingsystem.service.DishService;
import ru.gritandrey.restaurantvotingsystem.to.DishCreateTo;
import ru.gritandrey.restaurantvotingsystem.util.DishUtil;
import ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = AdminDishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tags({@Tag(name = "Admin Dish controller", description = "Manage dishes")})
public class AdminDishController {
    public static final String REST_URL = "/api/admin/dishes";
    private final DishService dishService;

    @GetMapping("{id}")
    public DishCreateTo get(@PathVariable int id) {
        DishCreateTo dish = DishUtil.getCreateTo(dishService.get(id));
        log.info("Get restaurant dish with id: {} {}", dish.getId(), dish);
        return dish;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishCreateTo> createWithLocation(@Valid @RequestBody DishCreateTo dishdishCreateToo) {
        ValidationUtil.checkNew(dishdishCreateToo);
        final var created = dishService.create(dishdishCreateToo);
        log.info("Create {}", created);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(DishUtil.getCreateTo(created));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishCreateTo dishCreateTo, @PathVariable int id) {
        ValidationUtil.assureIdConsistent(dishCreateTo, id);
        log.info("Update {}", dishCreateTo);
        dishService.update(dishCreateTo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete {}", id);
        dishService.delete(id);
    }
}
