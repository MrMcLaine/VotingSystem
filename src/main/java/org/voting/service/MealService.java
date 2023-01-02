package org.voting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.voting.entity.Meal;
import org.voting.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static org.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), restaurantId);
    }

    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must be not null");
        return repository.save(meal, restaurantId);
    }

    public void update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must be not null");
        checkNotFoundWithId(repository.save(meal, restaurantId), restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<Meal> getDateMenu(int restaurantId, LocalDate date) {
        return repository.getDateMenu(restaurantId, date);
    }

}
