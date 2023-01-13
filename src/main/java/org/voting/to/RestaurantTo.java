package org.voting.to;

import org.voting.entity.Meal;
import org.voting.entity.Restaurant;

import java.util.List;

public class RestaurantTo {
    private Integer id;
    private String name;
    private List<Meal> meals;

    public RestaurantTo() {
    }

    public RestaurantTo(Restaurant restaurant, List<Meal> meals) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.meals = meals;
    }
}
