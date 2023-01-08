package org.voting.web.json;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.voting.entity.person.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.voting.UserTestData.*;

class JsonUtilTest {
    private static final Logger log = LoggerFactory.getLogger(JsonUtilTest.class);

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(ADMIN);
        log.info(json);
        User user = JsonUtil.readValue(json, User.class);
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(users);
        log.info(json);
        List<User> actual = JsonUtil.readValues(json, User.class);
        USER_MATCHER.assertMatch(actual, users);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(USER);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = jsonWithPassword(USER, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}