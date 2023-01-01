package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.voting.entity.Vote;
import org.voting.repository.CrudVoteRepository;
import org.voting.repository.VoteRepository;

import java.time.LocalDateTime;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    @Override

    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote get(int userId, LocalDateTime dateTime) {
        return  repository.get(userId, dateTime);
    }
}
