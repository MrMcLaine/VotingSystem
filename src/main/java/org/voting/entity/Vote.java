package org.voting.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.voting.View;
import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.person.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
/*  ***Unable to create unique key constraint (user_id, voting_date_time) on table votes: database column 'voting_date_time' not found***
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(name="vote_idx",columnNames ={"user_id", "voting_date_time"})})
*/
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    @Transient
    @JoinColumn(name = "voting_date_time", nullable = false)
    private LocalDateTime votingDateTime = LocalDateTime.now();

    public Vote() {
    }

    public Vote(Restaurant restaurant) {
        this(null, restaurant);
    }

    public Vote(Integer id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
    }

    public Vote(int id, Restaurant restaurant) {
        super(id);
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
