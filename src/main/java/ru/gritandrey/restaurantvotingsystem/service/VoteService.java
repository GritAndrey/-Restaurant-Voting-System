package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import ru.gritandrey.restaurantvotingsystem.exception.IllegalRequestDataException;
import ru.gritandrey.restaurantvotingsystem.model.Vote;
import ru.gritandrey.restaurantvotingsystem.repository.RestaurantRepository;
import ru.gritandrey.restaurantvotingsystem.repository.UserRepository;
import ru.gritandrey.restaurantvotingsystem.repository.VoteRepository;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;
import ru.gritandrey.restaurantvotingsystem.util.VoteUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    @Value("${voting.voteEnding}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime voteEndTime;

    public VoteTo get(int voteId, int userId) {
        return VoteUtil.getTo(voteRepository.get(voteId, userId)
                .orElseThrow(() -> new IllegalRequestDataException("Entity with id=" + voteId + " not found")));
    }

    public List<VoteTo> getAll() {
        return voteRepository.findAll().stream().map(VoteUtil::getTo).collect(toList());
    }

    public List<VoteTo> getAllByUserId(int userId) {
        return VoteUtil.getTos(voteRepository.findAllByUserId(userId));
    }

    public VoteTo create(int restaurantId, int userId) {
        if (voteRepository.findByUserIdAndDate(userId, LocalDate.now()).isPresent()) {
            throw new IllegalRequestDataException("User Already voted");
        }
        final var vote = Vote.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .restaurant(restaurantRepository.getExisted(restaurantId))
                .user(userRepository.getExisted(userId))
                .build();
        return VoteUtil.getTo(save(vote));
    }

    public void update(Integer userId, Integer restaurantId) {
        if (LocalTime.now().isAfter(voteEndTime)) {
            throw new IllegalRequestDataException("Update Vote time is over");
        }
        final var mayBeVote = voteRepository.findByUserIdAndDate(userId, LocalDate.now());
        final var vote = mayBeVote.orElseThrow(() -> new IllegalRequestDataException("User haven`t voted yet"));
        vote.setTime(LocalTime.now());
        vote.setRestaurant(restaurantRepository.getExisted(restaurantId));
        save(vote);
    }

    public void delete(int id) {
        voteRepository.deleteExisted(id);
    }

    private Vote save(Vote vote) {
        return voteRepository.save(vote);
    }
}
