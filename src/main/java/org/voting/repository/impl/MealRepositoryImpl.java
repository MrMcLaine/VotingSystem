package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.Meal;
import org.voting.repository.CrudMealRepository;
import org.voting.repository.CrudRestaurantRepository;
import org.voting.repository.MealRepository;

import java.util.List;

import static org.voting.util.RestaurantUtil.deleteProxy;

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
        meal.setRestaurant(deleteProxy(crudRestaurantRepository.getReferenceById(restaurantId)));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public List<Meal> getActualMenu(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }
}
