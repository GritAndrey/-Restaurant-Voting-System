package ru.gritandrey.restaurantvotingsystem.util;


import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.User;

import static ru.gritandrey.restaurantvotingsystem.config.SecurityConfiguration.PASSWORD_ENCODER;

@UtilityClass
public class UserUtil {

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}