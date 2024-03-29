package org.voting;

import org.voting.entity.Vote;

import static org.voting.RestaurantTestData.*;
import static org.voting.UserTestData.*;
import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory
            .usingIgnoringFieldsComparator(Vote.class, "votingTime", "user.password", "user.id");

    public static final int VOTE_USER_ID = START_SEQ + 18;
    public static final int VOTE_ADMIN_ID = START_SEQ + 19;
    public static final int VOTE_JAMES_ID = START_SEQ + 20;
    public static final int VOTE_TEMP_USER_ID = START_SEQ + 23;

    public static Vote voteUserToday = new Vote(VOTE_USER_ID, central, USER);
    public static Vote voteAdminToday = new Vote(VOTE_ADMIN_ID, bonBon, ADMIN);
    public static Vote voteJamesToday = new Vote(VOTE_JAMES_ID, bonBon, JAMES);
    public static Vote voteTempUserToday = new Vote(VOTE_TEMP_USER_ID, central, tempUser);

    public static Vote getNew() {
        return new Vote(null, central);
    }
}
