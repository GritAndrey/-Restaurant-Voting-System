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
import ru.gritandrey.restaurantvotingsystem.util.mapper.VoteMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    @Value("${voting.voteEnding}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime VOTE_END_TIME;

    public VoteTo get(int voteId, int userId) {
        return VoteMapper.getTo(checkNotFoundWithId(voteRepository.findByUserIdAndId(userId, voteId), voteId));
    }

    public List<VoteTo> getAll() {
        return voteRepository.findAll().stream().map(VoteMapper::getTo).collect(toList());
    }

    public List<VoteTo> getAllByUserId(int userId) {
        return VoteMapper.getTos(voteRepository.findAllByUserId(userId));
    }

    public VoteTo create(int restaurantId, int userId) {
        if (voteRepository.findByUserIdAndDate(userId, LocalDate.now()).isPresent()) {
            throw new IllegalRequestDataException("User Already voted");
        }
        final var vote = Vote.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .restaurant(checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId))
                .user(checkNotFoundWithId(userRepository.findById(userId), userId))
                .build();
        return VoteMapper.getTo(save(vote));
    }

    public void update(Integer userId, Integer restaurantId) {
        if (LocalTime.now().isAfter(VOTE_END_TIME)) {
            throw new IllegalRequestDataException("Update Vote time is over");
        }
        final var mayBeVote = voteRepository.findByUserIdAndDate(userId, LocalDate.now());
        final var vote = mayBeVote.orElseThrow(() -> new IllegalRequestDataException("User haven`t voted yet"));
        vote.setTime(LocalTime.now());
        vote.setRestaurant(checkNotFoundWithId(restaurantRepository.findById(restaurantId), restaurantId));
        save(vote);
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id) != 0, id);
    }

    private Vote save(Vote vote) {
        return voteRepository.save(vote);
    }
}
