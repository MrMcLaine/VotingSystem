package org.voting.to;

import org.voting.entity.Restaurant;

import java.util.List;

public class RestaurantTo {
    private Integer id;
    private String name;
    private List<MealTo> mealsTo;

    public RestaurantTo() {
    }

    public RestaurantTo(Restaurant restaurant, List<MealTo> mealsTo) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.mealsTo = mealsTo;
    }
}
