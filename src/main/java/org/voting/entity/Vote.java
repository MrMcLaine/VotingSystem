package org.voting.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.voting.View;
import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.person.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

/*    @Column(name="voting_date_time")
    private LocalDateTime votingDateTime = LocalDateTime.now();*/

    @Column(name = "voting_date", columnDefinition = "date default now()")
    private LocalDate votingDate = LocalDate.now();

    @Transient
    private LocalTime votingTime = LocalTime.now();

    public Vote() {
    }

    public Vote(Restaurant restaurant) {
        this(null, restaurant);
    }

    public Vote(Integer id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
    }

    public Vote(Integer id, Restaurant restaurant, User user) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(Restaurant restaurant, LocalDateTime dateTimeVote) {
        this(null,restaurant,dateTimeVote);
    }

    public Vote(Integer id,Restaurant restaurant,LocalDateTime dateTimeVote){
        super(id);
        this.votingDate= dateTimeVote.toLocalDate();
        this.votingTime=dateTimeVote.toLocalTime();
        this.restaurant=restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(LocalDate votingDate) {
        this.votingDate = votingDate;
    }

    public LocalTime getVotingTime() {
        return votingTime;
    }

    public void setVotingTime(LocalTime votingTime) {
        this.votingTime = votingTime;
    }

    @Override
    public String toString() {
        return "Vote{" +
               "user=" + user +
               ", restaurant=" + restaurant +
               ", votingDateTime=" + votingDate +
               ", id=" + id +
               '}';
    }
}
