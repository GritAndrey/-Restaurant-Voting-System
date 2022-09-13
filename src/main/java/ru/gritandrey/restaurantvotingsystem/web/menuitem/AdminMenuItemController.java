package ru.gritandrey.restaurantvotingsystem.web.menuitem;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gritandrey.restaurantvotingsystem.model.MenuItem;
import ru.gritandrey.restaurantvotingsystem.service.MenuItemService;
import ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = AdminMenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tags({@Tag(name = "Admin MenuItem controller", description = "Manage dishes")})
public class AdminMenuItemController {
    public static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menu-items";
    private final MenuItemService menuItemService;

    @GetMapping("/{id}")
    public MenuItem get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("Get restaurant dish with id: {} restaurantId: {}", id, restaurantId);
        return menuItemService.get(id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItem> createWithLocation(@Valid @RequestBody MenuItem menuItem, @PathVariable int restaurantId) {
        log.info("Create {}", menuItem);
        ValidationUtil.checkNew(menuItem);
        final var created = menuItemService.create(menuItem, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MenuItem menuItem, @PathVariable int id, @PathVariable int restaurantId) {
        log.info("Update {}", menuItem);
        ValidationUtil.assureIdConsistent(menuItem, id);
        menuItemService.update(menuItem, id, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("Delete {}", id);
        menuItemService.delete(id, restaurantId);
    }
}
