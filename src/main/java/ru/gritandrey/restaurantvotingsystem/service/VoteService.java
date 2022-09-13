package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    @Value("${voting.voteEnding}")
    @Setter
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime voteEndTime;

    public VoteTo get(int voteId, int userId) {
        return VoteUtil.getTo(voteRepository.get(voteId, userId)
                .orElseThrow(() -> new IllegalRequestDataException("Entity with id=" + voteId + " not found")));
    }

    public VoteTo getTodayVote(int userId) {
        return VoteUtil.getTo(voteRepository.findByUserIdAndDate(userId, LocalDate.now())
                .orElseThrow(() -> new IllegalRequestDataException("No Vote today")));
    }

    public List<VoteTo> getAllByUserId(int userId) {
        return VoteUtil.getTos(voteRepository.findAllByUserId(userId));
    }

    @Transactional
    public VoteTo create(int restaurantId, int userId) {
        final var vote = Vote.builder()
                .date(LocalDate.now())
                .time(LocalTime.now())
                .restaurant(restaurantRepository.getExisted(restaurantId))
                .user(userRepository.getExisted(userId))
                .build();
        return VoteUtil.getTo(voteRepository.save(vote));
    }

    @Transactional
    public void update(Integer userId, Integer restaurantId, Integer voteId) {
        voteRepository.checkBelong(voteId, userId);
        if (LocalTime.now().isAfter(voteEndTime)) {
            throw new IllegalRequestDataException("Update Vote time is over");
        }
        final Optional<Vote> mayBeVote = voteRepository.findByUserIdAndDate(userId, LocalDate.now());
        final var vote = mayBeVote.orElseThrow(() -> new IllegalRequestDataException("User haven`t voted yet"));
        vote.setTime(LocalTime.now());
        vote.setRestaurant(restaurantRepository.getExisted(restaurantId));
    }

    public void delete(int id) {
        voteRepository.deleteExisted(id);
    }

}
