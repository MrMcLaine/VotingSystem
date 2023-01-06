package org.voting.repository;

import org.voting.entity.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote);
    Vote getVoteByUser(int userId, LocalDate date);
    List<Vote> getVotesByRestaurant(int restaurantId);
}
