package org.voting;

import org.voting.entity.Vote;

import java.util.List;

import static org.voting.RestaurantTestData.*;
import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");

    public static final int VOTE_USER_ID = START_SEQ + 18;
    public static final int VOTE_ADMIN_ID = START_SEQ + 19;
    public static final int VOTE_JAMES_ID = START_SEQ + 20;
    public static final int VOTE_DAVID_ID = START_SEQ + 21;

    public static final Vote voteUserToday = new Vote(VOTE_USER_ID, central);
    public static final Vote voteAdminToday = new Vote(VOTE_ADMIN_ID, bonBon);
    public static final Vote voteJamesToday = new Vote(VOTE_JAMES_ID, bonBon);
    public static final Vote voteDavidToday = new Vote(VOTE_DAVID_ID, atelierCrenn);

    public static Vote getNew() {
        return new Vote(null, central);
    }

    public static final List<Vote> votesBonbonToday = List.of(voteAdminToday, voteJamesToday);
}
