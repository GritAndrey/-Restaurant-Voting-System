package ru.gritandrey.restaurantvotingsystem.web.controller.restaurant;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.web.controller.AbstractControllerTest;
import ru.gritandrey.restaurantvotingsystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;
import static ru.gritandrey.restaurantvotingsystem.TestUtil.userHttpBasic;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.admin;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.user;

@RequiredArgsConstructor
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AdminRestaurantRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = AdminRestaurantRestController.REST_URL + "/";
    private final RestaurantService restaurantService;

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isForbidden());
    }

    @Test
    void createWithLocation() throws Exception {
        final var newRestaurant = getNewRestaurant();
        final var newRestaurantTo = getNewRestaurantTo();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(newRestaurantTo)))
                .andExpect(status().isCreated());
        final var created = RESTAURANT_TO_MATCHER.readFromJson(action);

        int newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}