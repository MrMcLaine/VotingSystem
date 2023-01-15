package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Meal;
import org.voting.entity.Restaurant;
import org.voting.repository.CrudMealRepository;
import org.voting.repository.CrudRestaurantRepository;
import org.voting.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class MealRepositoryImpl implements MealRepository {
    @Autowired
    CrudMealRepository crudMealRepository;
    @Autowired
    CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Meal get(int id, int restaurantId) {
        return crudMealRepository
                .findById(id)
                .filter(meal -> meal.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.id(), restaurantId) == null) {
            return null;
        }
        Restaurant t = (crudRestaurantRepository.get(restaurantId));
        meal.setRestaurant(t);
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }


    @Override
    public List<Meal> getMenuForDate(int restaurantId, LocalDate date) {
        return crudMealRepository.getMenuForDate(restaurantId, date);
    }

    @Override
    public List<Meal> getHistoryOfMenu(int restaurantId) {
        return crudMealRepository.getHistoryOfMenu(restaurantId);
    }
}
