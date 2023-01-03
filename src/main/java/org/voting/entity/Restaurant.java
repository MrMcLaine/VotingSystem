package org.voting.entity;

import org.voting.entity.abstractEntity.AbstractNamedEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants",uniqueConstraints = {@UniqueConstraint(name="restaurant_unique_name_idx",columnNames ="name")})
public class Restaurant extends AbstractNamedEntity {
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(),restaurant.getName(), restaurant.getMeals());
    }

    public Restaurant(Integer id, String name, List<Meal> meals) {
        super(id, name);
        setMeals(meals);
    }

    public Restaurant(String name){
        this(null,name);
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
