package org.voting.entity;

import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.abstractEntity.AbstractNamedEntity;

import java.util.List;

public class Restaurant extends AbstractNamedEntity {
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
               "meals=" + meals +
               ", name='" + name + '\'' +
               ", id=" + id +
               '}';
    }
}
