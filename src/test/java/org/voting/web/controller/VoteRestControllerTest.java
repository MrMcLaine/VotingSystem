package org.voting.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.voting.VoteTestData;
import org.voting.entity.Vote;
import org.voting.entity.person.Role;
import org.voting.entity.person.User;
import org.voting.service.VoteService;
import org.voting.web.AbstractControllerTest;
import org.voting.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.voting.RestaurantTestData.BONBON_ID;
import static org.voting.RestaurantTestData.CENTRAL_ID;
import static org.voting.TestUtil.userHttpBasic;
import static org.voting.UserTestData.*;
import static org.voting.VoteTestData.*;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    void vote() throws Exception {
        User TEMP_USER = new User(TEMP_USER_ID, "Temp User",
                "temp@gmail.com", "password", Role.USER);
        Vote newVote = VoteTestData.getNew();
        newVote.setUser(TEMP_USER);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + CENTRAL_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEMP_USER))
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(service.getTodayVoteByUser(TEMP_USER.getId()), newVote);
    }


    @Test
    void getTodayVote() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/today")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        VOTE_MATCHER.assertMatch(service.getTodayVoteByUser(USER.getId()), voteUserToday);
    }

    @Test
    void getVotesByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + BONBON_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        voteAdminToday.setVotingDate(LocalDate.of(2022, 12, 31));
        VOTE_MATCHER.assertMatch(service.getVotesByRestaurant(BONBON_ID), List.of(voteJamesToday, voteAdminToday));
    }
}