package org.voting.repository;

import org.voting.entity.Vote;

import java.time.LocalDate;

public interface VoteRepository {
    Vote save(Vote vote);
    Vote get(int userId, LocalDate date);
    }
