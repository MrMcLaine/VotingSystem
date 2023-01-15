package org.voting;

import org.voting.entity.Meal;
import org.voting.to.MealTo;

import java.util.List;

import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final MatcherFactory.Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "restaurant");
    public static final MatcherFactory.Matcher<MealTo> MEAL_TO_MATCHER = MatcherFactory.usingEqualsComparator(MealTo.class);

    public static final int NOT_FOUND = 10;
    public static final int MEAL1_CENTRAL_ID = START_SEQ + 7;
    public static final int MEAL1_ATELIERCRENN_ID = START_SEQ + 13;

    public static final Meal getMealCentral = new Meal(MEAL1_CENTRAL_ID,
            "Marine Soil (-20 M)", 30);
    public static final MealTo mealCentral1 = new MealTo(new Meal(MEAL1_CENTRAL_ID,
            "Marine Soil (-20 M)", 30));
    public static final MealTo mealCentral2 = new MealTo(new Meal(MEAL1_CENTRAL_ID + 1,
            "Low Andes Mountain (1800 M)", 44));
    public static final MealTo mealCentral3 = new MealTo(new Meal(MEAL1_CENTRAL_ID + 2,
            "Extreme Stem (2875 M)", 26));
    public static final MealTo mealAtelierCrenn1 = new MealTo(new Meal(MEAL1_ATELIERCRENN_ID,
            "Daisy Flower", 33));
    public static final MealTo mealAtelierCrenn2 = new MealTo(new Meal(MEAL1_ATELIERCRENN_ID + 1,
            "Love to Eat Avocado Toast", 68));
    public static final MealTo mealAtelierCrenn3 = new MealTo(new Meal(MEAL1_ATELIERCRENN_ID + 2,
            "Shining star", 49));
    public static final MealTo mealAtelierCrenn4 = new MealTo(new Meal(MEAL1_ATELIERCRENN_ID + 3,
            "Some test meal in past", 1));
    public static final MealTo mealAtelierCrenn5 = new MealTo(new Meal(MEAL1_ATELIERCRENN_ID + 4,
            "Second test meal in past", 1));

    public static final List<MealTo> mealsToCentralForToday = List.of(mealCentral1, mealCentral2, mealCentral3);

    public static final List<MealTo> mealsAtelierCrennAllDates = List.of(mealAtelierCrenn1, mealAtelierCrenn2,
            mealAtelierCrenn3, mealAtelierCrenn4, mealAtelierCrenn5);

    public static Meal getNew() {
        return new Meal(null, "New Meal", 2);
    }

    public static Meal getMealUpdated() {
        return new Meal(MEAL1_CENTRAL_ID, getMealCentral.getDateMeal().plusDays(2),
                "Updated Meal", 31);
    }
}
