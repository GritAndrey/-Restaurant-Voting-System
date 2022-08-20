package ru.gritandrey.restaurantvotingsystem.web.controller.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.gritandrey.restaurantvotingsystem.exception.NotFoundException;
import ru.gritandrey.restaurantvotingsystem.service.VoteService;
import ru.gritandrey.restaurantvotingsystem.util.mapper.VoteMapper;
import ru.gritandrey.restaurantvotingsystem.web.controller.AbstractControllerTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.*;
import static ru.gritandrey.restaurantvotingsystem.VoteTestData.*;

class AdminVoteRestControllerTest extends AbstractControllerTest {

    private final VoteService voteService;
    private static final String REST_URL = AdminVoteRestController.REST_URL + '/';

    public AdminVoteRestControllerTest(MockMvc mockMvc, VoteService voteService) {
        super(mockMvc);
        this.voteService = voteService;
    }


    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteMapper.getTos(List.of(adminVote, userVote, adminVote2))));
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
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + USER_VOTE_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> voteService.get(USER_VOTE_ID, USER_ID));
    }
}