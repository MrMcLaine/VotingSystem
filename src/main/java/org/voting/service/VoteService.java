package org.voting.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.voting.entity.Vote;
import org.voting.repository.RestaurantRepository;
import org.voting.repository.UserRepository;
import org.voting.repository.VoteRepository;
import org.voting.util.exception.VotingTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VoteService {

    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository,
                       RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote getTodayVoteByUser(int userId) {
        return voteRepository.getVoteByUser(userId, LocalDate.now());
    }

    public Vote save(int restaurantId, int userId) {
        if (isLegal(userId)) {
            return voteRepository.save(userId, restaurantId);
        } else {
            throw new VotingTimeException("Sorry, but voting time is over.");
        }
    }

    private boolean isLegal(int userId) {
        Vote getVote = getTodayVoteByUser(userId);
        return getVote == null || getVote.getVotingTime().isBefore(LocalTime.of(11, 0));
    }

    public List<Vote> getVotesByRestaurant(int restaurantId) {
        return restaurantRepository.get(restaurantId) == null ? null : voteRepository.getVotesByRestaurant(restaurantId);
    }
}
