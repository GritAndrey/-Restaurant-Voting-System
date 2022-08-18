package ru.gritandrey.restaurantvotingsystem;

import ru.gritandrey.restaurantvotingsystem.model.Role;
import ru.gritandrey.restaurantvotingsystem.model.User;

public class UserTestData {
    public static final String USER_MAIL = "user@gmail.com";
    public static final Integer USER_ID = 1;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String NEW_USER_EMAIL = "new@gmail.com";
    public static final String NO_VOTE_USER_EMAIL = "novote@gmail.com";
    public static final Integer NO_VOTE_USER_ID = 3;

    public static final User newUser = new User(3, "newUser", NEW_USER_EMAIL, "newPass", Role.USER);
    public static final User user = new User(1, "user", USER_MAIL, "password", Role.USER);
    public static final User admin = new User(2, "admin", ADMIN_MAIL, "admin", Role.USER, Role.ADMIN);

}
