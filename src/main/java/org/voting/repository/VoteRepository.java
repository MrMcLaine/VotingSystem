package org.voting.repository;

import org.voting.entity.Vote;

import java.time.LocalDateTime;

public interface VoteRepository {
    Vote save(Vote vote);
    Vote get(int userId, LocalDateTime dateTime);
    }
