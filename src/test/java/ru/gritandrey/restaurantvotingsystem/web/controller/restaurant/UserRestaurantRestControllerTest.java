package ru.gritandrey.restaurantvotingsystem.web.controller.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.gritandrey.restaurantvotingsystem.service.RestaurantService;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;
import ru.gritandrey.restaurantvotingsystem.web.controller.AbstractControllerTest;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gritandrey.restaurantvotingsystem.RestaurantAndDishTestData.*;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.USER_MAIL;

class UserRestaurantRestControllerTest extends AbstractControllerTest {
    private final RestaurantService restaurantService;
    public static final String REST_URL = UserRestaurantRestController.REST_URL + '/';

    public UserRestaurantRestControllerTest(MockMvc mockMvc, RestaurantService restaurantService) {
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
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(restaurant1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllWithTodayMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/menu"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(RESTAURANT_WITH_MENU_TO_MATCHER.contentJson(RestaurantMapper.getWithMenuTos(
                        List.of(restaurant1, restaurant2, restaurant3, restaurant4)
                )));
    }
}