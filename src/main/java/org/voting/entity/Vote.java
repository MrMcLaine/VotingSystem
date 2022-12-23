package org.voting.entity;

import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.person.User;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private User user;
    private Restaurant restaurant;
    private LocalDateTime votingDateTime;

    public Vote() {
    }

    public Vote(Restaurant restaurant) {
        this(null, restaurant);
    }

    public Vote(Integer id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
    }

    public Vote(Restaurant restaurant, LocalDateTime votingDateTime) {
        this.restaurant = restaurant;
        this.votingDateTime = votingDateTime;
    }

    public Vote(int id, Restaurant restaurant, LocalDateTime votingDateTime) {
        super(id);
        this.votingDateTime = votingDateTime;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
               "user=" + user +
               ", restaurant=" + restaurant +
               ", votingDateTime=" + votingDateTime +
               ", id=" + id +
               '}';
    }
}
