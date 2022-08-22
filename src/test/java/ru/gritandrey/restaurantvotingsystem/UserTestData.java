package ru.gritandrey.restaurantvotingsystem;

import ru.gritandrey.restaurantvotingsystem.model.Role;
import ru.gritandrey.restaurantvotingsystem.model.User;
import ru.gritandrey.restaurantvotingsystem.util.JsonUtil;

import java.util.Collections;
import java.util.Date;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");
    public static final String USER_MAIL = "user@gmail.com";
    public static final Integer USER_ID = 1;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final Integer ADMIN_ID = 2;
    public static final String NEW_USER_EMAIL = "new@gmail.com";
    public static final String NO_VOTE_USER_EMAIL = "novote@gmail.com";
    public static final Integer NO_VOTE_USER_ID = 3;

    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User noVoteUser = new User(NO_VOTE_USER_ID, "noVoteUser", NO_VOTE_USER_EMAIL, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.USER, Role.ADMIN);

    public static User getNew() {
        return new User(null, "newUser", NEW_USER_EMAIL, "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
