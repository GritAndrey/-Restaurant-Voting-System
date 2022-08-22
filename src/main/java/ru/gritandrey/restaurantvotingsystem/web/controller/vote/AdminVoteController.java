package ru.gritandrey.restaurantvotingsystem.web.controller.vote;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gritandrey.restaurantvotingsystem.service.VoteService;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tags({@Tag(name = "Admin votes controller", description = "Manage votes")})
public class AdminVoteController {
    public static final String REST_URL = "/api/admin/votes";
    private final VoteService voteService;

    @GetMapping()
    public List<VoteTo> getAll() {
        final var votes = voteService.getAll();
        log.info("GetAll Votes: {}", votes);
        return votes;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete vote {}", id);
        voteService.delete(id);
    }
}
