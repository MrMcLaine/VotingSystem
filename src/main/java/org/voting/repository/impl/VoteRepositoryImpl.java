package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.voting.entity.Vote;
import org.voting.repository.CrudVoteRepository;
import org.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote getVoteByUser(int userId, LocalDate date) {
        return repository.getVoteByUser(userId, date);
    }

    @Override
    public List<Vote> getVotesByRestaurant(int restaurantId) {
        return repository.getVotesByRestaurant(restaurantId);
    }
}
