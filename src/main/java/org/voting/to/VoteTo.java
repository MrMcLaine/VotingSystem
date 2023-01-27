package org.voting.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class VoteTo {

    @JsonIgnore
    private final Integer id;

    private final String email;

    private final LocalDate date;

    private final String restaurantName;

    public VoteTo(Integer id, String email, LocalDate date, String restaurantName) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.restaurantName = restaurantName;
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
