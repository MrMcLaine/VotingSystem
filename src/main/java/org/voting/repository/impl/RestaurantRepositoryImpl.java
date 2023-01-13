package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.voting.entity.Restaurant;
import org.voting.repository.CrudRestaurantRepository;
import org.voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    @Autowired
    CrudRestaurantRepository repository;

    @Override
    public Restaurant get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return repository.getWithMeals(id);
    }

    @Override
    public Restaurant getWithHistoryOfMeals(int id) {
        return repository.getWithHistoryOfMeals(id);
    }

    @Override
    public Restaurant getWithDaysHistoryOfMeals(int id, LocalDate date) {
        return repository.get(id, date);
    }
}
