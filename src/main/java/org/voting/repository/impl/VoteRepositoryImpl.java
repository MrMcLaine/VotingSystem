package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Vote;
import org.voting.repository.CrudRestaurantRepository;
import org.voting.repository.CrudUserRepository;
import org.voting.repository.CrudVoteRepository;
import org.voting.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository voteRepository;

    @Autowired
    private CrudUserRepository userRepository;

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote();
        vote.setUser(userRepository.findById(userId).orElse(null));
        vote.setRestaurant(restaurantRepository.get(restaurantId));
        return voteRepository.save(vote);
    }

    @Override
    public Vote update(Vote vote, int restaurantId) {
        vote.setRestaurant(restaurantRepository.get(restaurantId));
        return voteRepository.save(vote);
    }

    @Override
    public Vote getVoteByUser(int userId, LocalDate date) {
        return voteRepository.getVoteByUser(userId, date);
    }

    @Override
    public List<Vote> getVotesByRestaurant(int restaurantId) {
        return voteRepository.getVotesByRestaurant(restaurantId);
    }
}
