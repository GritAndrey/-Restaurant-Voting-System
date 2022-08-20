package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {

    @Query("select u from User u where u.email = lower(:email) ")
    Optional<User> findByEmailIgnoreCase(String email);
}
