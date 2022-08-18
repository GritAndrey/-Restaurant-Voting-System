package ru.gritandrey.restaurantvotingsystem.web.controller.vote;

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
import ru.gritandrey.restaurantvotingsystem.util.SecurityUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = UserVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tags({@Tag(name = "User votes controller", description = "Manage user votes")})
public class UserVoteRestController {
    public static final String REST_URL = "/api/rest/profile/votes";
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

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestParam int restaurantId) {
        final var userId = SecurityUtil.authId();
        log.info("Update vote. UserId: {} RestaurantId: {}", userId, restaurantId);
        voteService.update(userId, restaurantId);
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
