package org.voting.util;

import org.voting.entity.Vote;
import org.voting.to.VoteTo;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VotesUtil {

    public static final LocalTime END_OF_VOTING = LocalTime.of(11, 0);

    public static boolean isLegal(Vote vote) {
        return vote.getVotingTime().isBefore(END_OF_VOTING);
    }

    public static List<VoteTo> convertListToVoteTo(List<Vote> votes) {
        return votes.stream()
                .map(VotesUtil::convertToVoteTo)
                .collect(Collectors.toList());
    }

    public static VoteTo convertToVoteTo(Vote vote) {
        return new VoteTo(Objects.requireNonNull(vote).getId(), vote.getUser().getEmail(),
                vote.getVotingDate(), vote.getRestaurant().getName());
    }
}
