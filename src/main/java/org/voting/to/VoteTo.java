package org.voting.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import org.voting.entity.Vote;

import java.time.LocalDate;
import java.util.Objects;

public class VoteTo {

    @JsonIgnore
    private final Integer id;

    private final String email;

    private final LocalDate date;

    private String restaurantName;

    public VoteTo(@Nullable Vote vote) {
        this.id = Objects.requireNonNull(vote).getId();
        this.email = vote.getUser().getEmail();
        this.date = vote.getVotingDate();
        this.restaurantName = vote.getRestaurant().getName();
    }

    @Override
    public String toString() {
        return "VoteTo{" +
               "id=" + id +
               ", email='" + email + '\'' +
               ", date=" + date +
               ", restaurantName='" + restaurantName + '\'' +
               '}';
    }
}
