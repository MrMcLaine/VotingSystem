package org.voting.repository;

import org.voting.entity.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    Meal get(int id, int restaurantId);

    Meal save(Meal meal, int restaurantId);

    boolean delete(int id, int restaurantId);

    List<Meal> getDateMenu(int restaurantId, LocalDate date);
}
