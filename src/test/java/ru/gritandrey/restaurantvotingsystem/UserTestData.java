package ru.gritandrey.restaurantvotingsystem;

import ru.gritandrey.restaurantvotingsystem.model.Role;
import ru.gritandrey.restaurantvotingsystem.model.User;

public class UserTestData {
    public static final String USER_MAIL = "user@gmail.com";
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final User user =
            new User(1,
                    "user",
                    "user@gmail.com",
                    "password", Role.USER);
    public static final User admin =
            new User(2,
                    "admin",
                    "admin@gmail.com",
                    "admin", Role.USER, Role.ADMIN);

}
