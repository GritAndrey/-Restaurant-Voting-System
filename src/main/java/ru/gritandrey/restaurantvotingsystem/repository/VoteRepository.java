package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("delete from Vote v where v.id=:id")
    int delete(@Param("id") int id);

    @Query("select v from Vote v where v.user.id = :userId")
    List<Vote> findAllByUserId(Integer userId);

    @Query("select v from Vote v where v.date=:date and v.user.id=:userId")
    Optional<Vote> findByUserIdAndDate(Integer userId, LocalDate date);

    @Query("select v from Vote v where v.id =:id and v.user.id=:userId")
    Optional<Vote> findByUserIdAndId(Integer userId, Integer id);
}
