package org.voting.service;

import org.voting.entity.Vote;
import org.voting.repository.RestaurantRepository;
import org.voting.repository.UserRepository;
import org.voting.repository.VoteRepository;

import java.util.List;

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

    public Vote get(int id) {
        return null;
    }

    public Vote create(Vote vote){
        return null;
    }

    public Vote update(Vote vote){
        return null;
    }

    public List<Vote> getAllByRestaurant(int restaurantId){
        return null;
    }
}
