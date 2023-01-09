package org.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.voting.entity.Vote;

import static org.voting.UserTestData.USER_ID;
import static org.voting.VoteTestData.VOTE_MATCHER;
import static org.voting.VoteTestData.voteUserToday;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void getTodayVoteByUser() {
        Vote vote = service.getTodayVoteByUser(USER_ID);
        VOTE_MATCHER.assertMatch(vote, voteUserToday);
    }

    //give vote
    @Test
    void giveVote() {

    }

    //change vote
    @Test
    void changeVote(){

    }

    @Test
    void changeVoteAfter11(){

    }

    @Test
    void getVotesByRestaurant() {
    }
}