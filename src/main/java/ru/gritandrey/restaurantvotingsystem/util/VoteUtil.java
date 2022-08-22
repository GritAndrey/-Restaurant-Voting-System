package ru.gritandrey.restaurantvotingsystem.util;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Vote;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class VoteUtil {

    public static VoteTo getTo(Vote vote) {
        final var restaurant = vote.getRestaurant();
        final var user = vote.getUser();
        return VoteTo.builder()
                .id(vote.getId())
                .restaurantId(restaurant == null ? null : restaurant.getId())
                .userId(user == null ? null : user.getId())
                .date(vote.getDate())
                .time(vote.getTime())
                .build();
    }

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream().map(VoteUtil::getTo).collect(toList());
    }
}
