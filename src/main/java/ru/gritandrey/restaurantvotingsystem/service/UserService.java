package ru.gritandrey.restaurantvotingsystem.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.gritandrey.restaurantvotingsystem.model.User;
import ru.gritandrey.restaurantvotingsystem.repository.UserRepository;
import ru.gritandrey.restaurantvotingsystem.to.UserTo;

import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.mapper.UserMapper.updateFromTo;
import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFound;
import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNotFoundWithId;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.findByEmailIgnoreCase(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        User updatedUser = updateFromTo(user, userTo);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }
}