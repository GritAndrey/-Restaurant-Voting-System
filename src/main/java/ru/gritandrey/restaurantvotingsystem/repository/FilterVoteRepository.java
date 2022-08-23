package ru.gritandrey.restaurantvotingsystem.repository;

import ru.gritandrey.restaurantvotingsystem.model.Vote;
import ru.gritandrey.restaurantvotingsystem.to.VoteFilter;

import java.util.List;

public interface FilterVoteRepository {

    List<Vote> findAllByFilter(VoteFilter filter);
}
