package org.voting.entity;

import java.time.LocalDateTime;

public class Vote{
    private final int restaurantId;
    private final int userId;
    private final LocalDateTime votingDateTime;

    public Vote(int restaurantId, int userId, LocalDateTime votingDateTime) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.votingDateTime = votingDateTime;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getVotingDateTime() {
        return votingDateTime;
    }
}
