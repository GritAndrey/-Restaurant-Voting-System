package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.exception.DataConflictException;
import ru.gritandrey.restaurantvotingsystem.model.MenuItem;
import ru.gritandrey.restaurantvotingsystem.to.MenuItemFilter;
import ru.gritandrey.restaurantvotingsystem.to.MenuItemTo;
import ru.gritandrey.restaurantvotingsystem.util.MenuItemUtil;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;

@DisplayName("MenuItem service crud test")
@RequiredArgsConstructor
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MenuItemServiceTest {

    private final MenuItemService menuItemService;

    @Test
    @DisplayName("Get one dish")
    void get() {
        final var dish = menuItemService.get(DISH1_ID, RESTAURANT1_ID);
        DISH_MATCHER.assertMatch(dish, MENU_ITEM_1);
    }

    @Test
    @DisplayName("Get all today MENU_ITEMS")
    void getAllToday() {
        final var actual = menuItemService.getByFilter(new MenuItemFilter(null, TODAY, null)).stream()
                .flatMap(menuTo -> menuTo.getDishes().stream())
                .sorted(comparing(MenuItemTo::getId))
                .collect(toList());
        DISH_TO_MATCHER.assertMatch(actual, MenuItemUtil.getTos(MENU_ITEMS));
    }

    @Test
    @DisplayName("Get MenuItem with fake id. Must be DataConflictException")
    void getNotFound() {
        assertThrows(DataConflictException.class, () -> menuItemService.get(NOT_FOUND, RESTAURANT1_ID));
    }

    @Test
    @DisplayName("Checking new dish creation")
    void create() {
        final var newDish = getNewDishWithExistingNameAndRestaurant();
        final MenuItem created = menuItemService.create(newDish, newDish.getRestaurant().getId());
        final var newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(menuItemService.get(newId, RESTAURANT1_ID), newDish);
    }

    @Test
    @DisplayName("Update MENU_ITEM_1")
    void update() {
        final MenuItem updatedMenuItem = getUpdatedDish();
        menuItemService.update(updatedMenuItem, updatedMenuItem.getId(), updatedMenuItem.getRestaurant().getId());
        DISH_MATCHER.assertMatch(menuItemService.get(DISH1_ID, RESTAURANT1_ID), updatedMenuItem);
    }

    @Test
    @DisplayName("Delete MENU_ITEM_1")
    void delete() {
        menuItemService.delete(DISH1_ID, RESTAURANT1_ID);
        assertThrows(DataConflictException.class, () -> menuItemService.get(DISH1_ID, RESTAURANT1_ID));
    }
}
