package org.voting.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.voting.service.UserService;
import org.voting.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.voting.TestUtil.userHttpBasic;
import static org.voting.UserTestData.*;

class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Autowired
    private UserService userService;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(admin, james, user, david));
    }

    @Test
    void get() {
    }

    @Test
    void createWithLocation() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getByMail() {
    }

    @Test
    void enable() {
    }
}