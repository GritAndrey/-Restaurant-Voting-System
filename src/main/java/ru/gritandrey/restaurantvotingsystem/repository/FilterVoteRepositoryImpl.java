package ru.gritandrey.restaurantvotingsystem.repository;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.Vote;
import ru.gritandrey.restaurantvotingsystem.repository.querydsl.QPredicates;
import ru.gritandrey.restaurantvotingsystem.to.VoteFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.model.QVote.vote;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilterVoteRepositoryImpl implements FilterVoteRepository {
    private final EntityManager entityManager;

    @Override
    public List<Vote> findAllByFilter(VoteFilter filter) {
        final var predicate = QPredicates.builder()
                .add(filter.restaurantId(), vote.restaurant.id::eq)
                .add(filter.startDate(), vote.date::goe)
                .add(filter.endDate(), vote.date::loe)
                .add(filter.userId(), vote.user.id::eq)
                .build();
        return new JPAQuery<Vote>(entityManager)
                .select(vote)
                .from(vote)
                .where(predicate)
                .fetch();
    }
}
