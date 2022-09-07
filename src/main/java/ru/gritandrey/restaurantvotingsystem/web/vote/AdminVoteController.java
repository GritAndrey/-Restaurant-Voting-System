package ru.gritandrey.restaurantvotingsystem.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gritandrey.restaurantvotingsystem.service.VoteService;
import ru.gritandrey.restaurantvotingsystem.to.VoteFilter;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;

import java.time.LocalDate;
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
    @Operation(summary = "GetAll votes by Restaurant Id and(or) period and(or) userId")
    public List<VoteTo> getByFilter(@RequestParam @Nullable Integer restaurantId,
                                    @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                            LocalDate startDate,
                                    @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                            LocalDate endDate,
                                    @RequestParam @Nullable Integer userId) {
        return voteService.getByFilter(new VoteFilter(restaurantId, startDate, endDate, userId));
    }
}
