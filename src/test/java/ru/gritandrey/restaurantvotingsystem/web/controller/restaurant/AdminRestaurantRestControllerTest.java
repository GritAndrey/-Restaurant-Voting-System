package ru.gritandrey.restaurantvotingsystem.web.controller.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.gritandrey.restaurantvotingsystem.exception.NotFoundException;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;
import ru.gritandrey.restaurantvotingsystem.web.controller.AbstractControllerTest;
import ru.gritandrey.restaurantvotingsystem.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.ADMIN_MAIL;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.USER_MAIL;


class AdminRestaurantRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = AdminRestaurantRestController.REST_URL + '/';
    private final RestaurantService restaurantService;

    public AdminRestaurantRestControllerTest(MockMvc mockMvc, RestaurantService restaurantService) {
        super(mockMvc);
        this.restaurantService = restaurantService;
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        final var newRestaurantTo = getNewRestaurantTo();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurantTo)))
                .andExpect(status().isCreated());
        final var created = RESTAURANT_TO_MATCHER.readFromJson(action);
        int newId = created.id();
        newRestaurantTo.setId(newId);
        RESTAURANT_TO_MATCHER.assertMatch(restaurantService.get(newId), newRestaurantTo);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> restaurantService.get(RESTAURANT1_ID));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        final var updated = RestaurantMapper.getTo(getUpdatedRestaurant());
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        RESTAURANT_TO_MATCHER.assertMatch(restaurantService.get(RESTAURANT1_ID), updated);
    }
}