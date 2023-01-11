package org.voting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.voting.entity.Vote;
import org.voting.to.VoteTo;

import java.util.ArrayList;
import java.util.List;

import static org.voting.RestaurantTestData.BONBON_ID;
import static org.voting.RestaurantTestData.CENTRAL_ID;
import static org.voting.UserTestData.USER;
import static org.voting.UserTestData.USER_ID;
import static org.voting.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void getTodayVoteByUser() {
        VoteTo voteToTest = new VoteTo(service.getTodayVoteByUser(USER_ID));
        voteUserToday.setUser(USER);
        VoteTo voteToData = new VoteTo(voteUserToday);
        Assertions.assertEquals(voteToTest, voteToData);
    }

    //give vote
    @Test
    void giveVote() {
        Vote vote = service.save(CENTRAL_ID, TEMP_USER.getId());
        VOTE_MATCHER.assertMatch(vote, voteTempUserToday);
        }

    //change vote
    @Test
    void changeVoteLegal(){

    }

    @Test
    void changeVoteAfter11(){

    }

    @Test
    void getVotesByRestaurant() {
        List<Vote> tempList = service.getVotesByRestaurant(BONBON_ID);
        List<VoteTo> votesToday = new ArrayList<>();
        List<VoteTo> votesToBonbonToday = new ArrayList<>();
        for(Vote vote : tempList) {
            votesToday.add(new VoteTo(vote));
        }
        for(Vote vote : votesBonbonToday) {
            votesToBonbonToday.add(new VoteTo(vote));
        }
        Assertions.assertEquals(votesToday, votesToBonbonToday);
    }
}