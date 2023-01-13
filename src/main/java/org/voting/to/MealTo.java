package org.voting.to;

import org.voting.entity.Meal;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealTo mealTo)) return false;

        if (!Objects.equals(id, mealTo.id)) return false;
        if (!Objects.equals(dateMeal, mealTo.dateMeal)) return false;
        if (!Objects.equals(description, mealTo.description)) return false;
        return Objects.equals(price, mealTo.price);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateMeal != null ? dateMeal.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
