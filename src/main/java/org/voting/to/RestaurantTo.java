package org.voting.to;

import org.voting.entity.Meal;

import java.util.List;

public class RestaurantTo {
    private Integer id;
    private String name;
    private List<Meal> meals;
    private Integer numberOfVotes;

    public RestaurantTo(Integer id, String name, List<Meal> meals, Integer numberOfVotes) {
        this.id = id;
        this.name = name;
        this.meals = meals;
        this.numberOfVotes = numberOfVotes;
    }
}
