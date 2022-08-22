package ru.gritandrey.restaurantvotingsystem.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.gritandrey.restaurantvotingsystem.exception.IllegalRequestDataException;
import ru.gritandrey.restaurantvotingsystem.service.VoteService;
import ru.gritandrey.restaurantvotingsystem.util.VoteUtil;
import ru.gritandrey.restaurantvotingsystem.web.AbstractControllerTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.gritandrey.restaurantvotingsystem.UserTestData.*;
import static ru.gritandrey.restaurantvotingsystem.VoteTestData.*;

class AdminVoteControllerTest extends AbstractControllerTest {

    private final VoteService voteService;
    private static final String REST_URL = AdminVoteController.REST_URL + '/';

    public AdminVoteControllerTest(MockMvc mockMvc, VoteService voteService) {
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
                .andExpect(VOTE_TO_MATCHER.contentJson(VoteUtil.getTos(List.of(adminVote, userVote, adminVote2))));
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
        assertThrows(IllegalRequestDataException.class, () -> voteService.get(USER_VOTE_ID, USER_ID));
    }
}