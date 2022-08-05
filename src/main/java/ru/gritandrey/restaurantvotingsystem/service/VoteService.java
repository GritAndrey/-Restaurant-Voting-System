package ru.gritandrey.restaurantvotingsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gritandrey.restaurantvotingsystem.model.Vote;
import ru.gritandrey.restaurantvotingsystem.repository.VoteRepository;
import ru.gritandrey.restaurantvotingsystem.to.VoteTo;

import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public Vote get(int id) {
        return null;
    }

    public List<Vote> getAllByUserId(int id) {
        return null;
    }

    public List<Vote> getAllByRestaurantId(int id) {
        return null;
    }

    public List<Vote> getAll() {
        return null;
    }

    public Vote create(VoteTo voteTo) {
        return null;
    }

    public void update(VoteTo voteTo) {
        //check time
        //if time is good then update
    }

    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id) != 0, id);
    }
}
