package org.voting.to;

import org.voting.entity.Meal;

import java.time.LocalDate;

public class MealTo {
    private Integer id;
    private LocalDate dateMeal;
    private String description;
    private Integer price;

    public MealTo(Meal meal) {
        this.id = meal.getId();
        this.dateMeal = meal.getDateMeal();
        this.description = meal.getDescription();
        this.price = meal.getPrice();
    }
}
