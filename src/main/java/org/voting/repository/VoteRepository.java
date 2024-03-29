package org.voting.repository;

import org.voting.entity.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(int userId, int restaurantId);
    Vote update(Vote vote, int restaurantId);
    Vote getVoteByUser(int userId, LocalDate date);
    List<Vote> getVotesByRestaurant(int restaurantId);
}
