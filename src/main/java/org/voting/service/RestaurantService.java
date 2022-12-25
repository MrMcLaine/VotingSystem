package org.voting.service;

import org.springframework.util.Assert;
import org.voting.entity.Restaurant;
import org.voting.repository.RestaurantRepository;

import java.util.List;

import static org.voting.util.ValidationUtil.checkNotFoundWithId;

public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must be not null");
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "meal must be not null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant getWithMeals(int id) {
        Restaurant restaurant = repository.getWithMeals(id);
        return restaurant != null ? restaurant : get(id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
