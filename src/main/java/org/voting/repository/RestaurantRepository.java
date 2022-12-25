package org.voting.repository;

import org.voting.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    List<Restaurant> getAll();

    Restaurant getWithMeals(int id);
}
