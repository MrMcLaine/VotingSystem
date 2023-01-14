package org.voting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.voting.entity.Vote;
import org.voting.entity.person.User;
import org.voting.to.VoteTo;

import java.time.LocalDate;
import java.util.List;

import static org.voting.RestaurantTestData.BONBON_ID;
import static org.voting.RestaurantTestData.CENTRAL_ID;
import static org.voting.UserTestData.*;
import static org.voting.VoteTestData.*;
import static org.voting.util.VotesUtil.convertListToVoteTo;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Autowired
    private UserService userService;

    @Test
    void getTodayVoteByUser() {
        VoteTo voteToTest = new VoteTo(service.getTodayVoteByUser(USER_ID));
        voteUserToday.setUser(USER);
        VoteTo voteToData = new VoteTo(voteUserToday);
        Assertions.assertEquals(voteToTest, voteToData);
    }

    @Test
    void vote() {
        User u = userService.create(TEMP_USER);
        Vote vote = service.save(CENTRAL_ID, u.getId());
        voteTempUserToday.setVotingDate(LocalDate.now());
        VOTE_MATCHER.assertMatch(vote, voteTempUserToday);
    }

    @Test
    void getVotesByRestaurant() {
        voteAdminToday.setVotingDate(LocalDate.of(2022, 12, 31));
        Assertions.assertEquals(convertListToVoteTo(service.getVotesByRestaurant(BONBON_ID)),
                convertListToVoteTo(List.of(voteJamesToday, voteAdminToday)));
    }
}