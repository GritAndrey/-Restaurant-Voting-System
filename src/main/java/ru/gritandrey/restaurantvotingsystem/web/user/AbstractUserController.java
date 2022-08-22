package ru.gritandrey.restaurantvotingsystem.web.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.gritandrey.restaurantvotingsystem.model.User;
import ru.gritandrey.restaurantvotingsystem.service.UserService;
import ru.gritandrey.restaurantvotingsystem.to.UserTo;
import ru.gritandrey.restaurantvotingsystem.util.UserUtil;

import java.util.List;

import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.assureIdConsistent;
import static ru.gritandrey.restaurantvotingsystem.util.validation.ValidationUtil.checkNew;


@Slf4j
public abstract class AbstractUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UniqueMailValidator emailValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
    }

    public List<User> getAll() {
        log.info("getAll");
        return userService.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return userService.get(id);
    }

    public User create(UserTo userTo) {
        log.info("create {}", userTo);
        checkNew(userTo);
        return userService.create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        userService.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userService.update(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        userService.update(userTo);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userService.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        userService.enable(id, enabled);
    }
}