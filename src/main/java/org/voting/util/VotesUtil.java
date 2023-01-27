package org.voting.util;

import org.voting.entity.Vote;
import org.voting.to.VoteTo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VotesUtil {

    public static final LocalTime END_OF_VOTING = LocalTime.of(11, 0);

    public static boolean isLegal(Vote vote) {
        return vote.getVotingTime().isBefore(END_OF_VOTING);
    }

    public static List<VoteTo> convertListToVoteTo(List<Vote> votes) {
        List<VoteTo> resultList = new ArrayList<>();
        for (Vote vote : votes) {
            resultList.add(new VoteTo(vote));
        }
        return resultList;
    }
}
