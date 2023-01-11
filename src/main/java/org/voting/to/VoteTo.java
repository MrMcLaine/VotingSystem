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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteTo voteTo)) return false;

        if (!id.equals(voteTo.id)) return false;
        if (!email.equals(voteTo.email)) return false;
        if (!date.equals(voteTo.date)) return false;
        return restaurantName.equals(voteTo.restaurantName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + restaurantName.hashCode();
        return result;
    }
}
