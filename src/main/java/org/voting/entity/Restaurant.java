package org.voting.entity;

import org.voting.entity.AbstractEntity;

import java.util.List;

public class Restaurant extends AbstractEntity {
    private List<Meal> meals;
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
