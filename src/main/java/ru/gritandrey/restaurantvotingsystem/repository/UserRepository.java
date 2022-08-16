package ru.gritandrey.restaurantvotingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gritandrey.restaurantvotingsystem.model.User;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("delete from Dish d where d.id=:id")
    int delete(@Param("id") int id);

    @Query("select u from User u where u.email = lower(:email) ")
    Optional<User> findByEmailIgnoreCase(String email);
}
