package org.voting;

import org.voting.entity.person.Role;
import org.voting.entity.person.User;
import org.voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.List;

import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class,  "password");

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int JAMES_ID = START_SEQ + 2;
    public static final int DAVID_ID = START_SEQ + 3;
    public static final int TEMP_USER_ID = START_SEQ + 22;
    public static final int NOT_FOUND = 10;

    public static final User USER = new User(USER_ID, "User", "user@gmail.com", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final User JAMES = new User(JAMES_ID, "James", "james21@gmail.com", "james", Role.USER);
    public static final User DAVID = new User(DAVID_ID, "David", "david_D@gmail.com", "david", Role.USER);
    public static final User TEMP_USER = new User(TEMP_USER_ID, "Temp User",
            "temp@gmail.com","password", Role.USER);
    public static final List<User> users = List.of(DAVID, JAMES, ADMIN, USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
/*
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int JAMES_ID = START_SEQ + 2;
    public static final int DAVID_ID = START_SEQ + 3;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@gmail.com", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User james = new User(JAMES_ID, "James", "james21@gmail.com", "james", Role.USER);
    public static final User david = new User(DAVID_ID, "David", "david_D@gmail.com", "david", Role.USER);


    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }*/
}
