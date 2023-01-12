package org.voting;

import org.voting.entity.Vote;
import org.voting.entity.person.Role;
import org.voting.entity.person.User;

import java.util.List;

import static org.voting.RestaurantTestData.*;
import static org.voting.UserTestData.*;
import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Vote.class, "votingTime", "restaurant.meals");

    public static final User TEMP_USER = new User(222222, "Temp User",
            "temp@gmail.com","password", Role.USER);

    public static final int VOTE_USER_ID = START_SEQ + 18;
    public static final int VOTE_ADMIN_ID = START_SEQ + 19;
    public static final int VOTE_JAMES_ID = START_SEQ + 20;
    public static final int VOTE_DAVID_ID = START_SEQ + 21;
    public static final int VOTE_TEMP_USER_ID = START_SEQ + 22;

    public static Vote voteUserToday = new Vote(VOTE_USER_ID, central, USER);
    public static Vote voteAdminToday = new Vote(VOTE_ADMIN_ID, bonBon, ADMIN);
    public static Vote voteJamesToday = new Vote(VOTE_JAMES_ID, bonBon, JAMES);
    public static Vote voteDavidToday = new Vote(VOTE_DAVID_ID, atelierCrenn, DAVID);
    public static Vote voteTempUserToday = new Vote(VOTE_TEMP_USER_ID, central, TEMP_USER);

    public static Vote getNew() {
        return new Vote(null, central);
    }

    public static final List<Vote> votesBonbonToday = List.of(voteAdminToday, voteJamesToday);
}
