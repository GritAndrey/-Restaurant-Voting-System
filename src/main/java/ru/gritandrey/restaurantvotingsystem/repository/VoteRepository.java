package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote>, FilterVoteRepository {

    @Query("select v from Vote v where v.user.id = :userId")
    List<Vote> findAllByUserId(Integer userId);

    @Query("select v from Vote v where v.date=:date and v.user.id=:userId")
    Optional<Vote> findByUserIdAndDate(Integer userId, LocalDate date);

    @Query("select v from Vote v where v.id = :id and v.user.id = :userId")
    Optional<Vote> get(int id, int userId);
}
