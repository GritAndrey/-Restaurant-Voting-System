package ru.gritandrey.restaurantvotingsystem.web.vote;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gritandrey.restaurantvotingsystem.service.VoteService;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;
import ru.gritandrey.restaurantvotingsystem.web.SecurityUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tags({@Tag(name = "User votes controller", description = "Manage user votes")})
public class UserVoteController {
    public static final String REST_URL = "/api/profile/votes";
    private final VoteService voteService;

    @GetMapping()
    public List<VoteTo> getAllByUserId() {
        final var userId = SecurityUtil.authId();
        log.info("Get user votes with userId {}", userId);
        return voteService.getAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public VoteTo get(@PathVariable int id) {
        final var userId = SecurityUtil.authId();
        final VoteTo vote = voteService.get(id, userId);
        log.info("Get Vote  {}  with userId {}", vote, userId);
        return vote;
    }

    @GetMapping("/today")
    public VoteTo getToday() {
        final var userId = SecurityUtil.authId();
        log.info("Get today vote  with userId {}", userId);
        return voteService.getTodayVote(userId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId, @PathVariable int id) {
        final var userId = SecurityUtil.authId();
        log.info("Update vote. UserId: {} RestaurantId: {}, voteId: {}", userId, restaurantId, id);
        voteService.update(userId, restaurantId, id);
    }

    @PostMapping()
    public ResponseEntity<VoteTo> voteForRestaurant(@RequestParam int restaurantId) {
        final var userId = SecurityUtil.authId();
        log.info("Create Vote.\nuserId: {}\nrestaurantId {}", userId, restaurantId);
        final var created = voteService.create(restaurantId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource)
                .body(created);
    }
}
