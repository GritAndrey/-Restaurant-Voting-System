package ru.gritandrey.restaurantvotingsystem.util.mapper;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.Vote;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class VoteMapper {

    public static VoteTo getTo(Vote vote) {
        return VoteTo.builder()
                .id(vote.getId())
                .restaurantId(vote.getRestaurant().getId())
                .userId(vote.getUser().getId())
                .date(vote.getDate())
                .time(vote.getTime())
                .build();
    }

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream().map(VoteMapper::getTo).collect(toList());
    }
}
