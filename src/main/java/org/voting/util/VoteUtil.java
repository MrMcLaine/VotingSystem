package org.voting.util;

import org.voting.entity.Vote;
import org.voting.to.VoteTo;

import java.util.ArrayList;
import java.util.List;

public class VoteUtil {
    public static List<VoteTo> convertListToVoteTo(List<Vote> votes) {
        List<VoteTo> resultList = new ArrayList<>();
        for(Vote vote : votes) {
            resultList.add(new VoteTo(vote));
        }
        return resultList;
    }
}
