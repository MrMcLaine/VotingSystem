package org.voting;

import org.voting.entity.Meal;
import org.voting.to.MealTo;

import java.util.List;

import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final MatcherFactory.Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "restaurant");

    public static final int NOT_FOUND = 10;
    public static final int MEAL1_CENTRAL_ID = START_SEQ + 7;
    public static final int MEAL1_BONBON_ID = START_SEQ + 10;
    public static final int MEAL1_ATELIERCRENN_ID = START_SEQ + 13;

    public static final Meal getMealCentral = new Meal(MEAL1_CENTRAL_ID,
            "Marine Soil (-20 M)", 30);
    public static final MealTo mealCentral1 = new MealTo(new Meal(MEAL1_CENTRAL_ID,
            "Marine Soil (-20 M)", 30));
    public static final MealTo mealCentral2 = new MealTo(new Meal(MEAL1_CENTRAL_ID + 1,
            "Low Andes Mountain (1800 M)", 44));
    public static final MealTo mealCentral3 = new MealTo(new Meal(MEAL1_CENTRAL_ID + 2,
            "Extreme Stem (2875 M)", 26));

    public static final Meal mealBonBon1 = new Meal(MEAL1_BONBON_ID,
            "gazpacho with \"snow\"", 28);
    public static final Meal mealBonBon2 = new Meal(MEAL1_BONBON_ID + 1,
            "Jewels of ‘White Pearl’ oysters with Corsican mint and Vodka-tonic gels", 77);
    public static final Meal mealBonBon3 = new Meal(MEAL1_BONBON_ID + 2,
            "Salted Pollack from Yeu Island with pine salt and fermented cabbage", 34);

    public static final Meal mealAtelierCrenn1 = new Meal(MEAL1_ATELIERCRENN_ID,
            "Daisy Flower", 33);
    public static final Meal mealAtelierCrenn2 = new Meal(MEAL1_ATELIERCRENN_ID + 1,
            "Love to Eat Avocado Toast", 68);
    public static final Meal mealAtelierCrenn3 = new Meal(MEAL1_ATELIERCRENN_ID + 2,
            "Shining star", 49);
    public static final Meal mealAtelierCrenn4 = new Meal(MEAL1_ATELIERCRENN_ID + 3,
            "Some test meal in past", 1);
    public static final Meal mealAtelierCrenn5 = new Meal(MEAL1_ATELIERCRENN_ID + 4,
            "Second test meal in past", 1);

    public static final List<MealTo> mealsToCentralForToday = List.of(mealCentral1, mealCentral2, mealCentral3);

    public static final List<Meal> mealsAtelierCrennAllDates = List.of(mealAtelierCrenn1, mealAtelierCrenn2,
            mealAtelierCrenn3, mealAtelierCrenn4, mealAtelierCrenn5);

    public static Meal getNew() {
        return new Meal(null, "New Meal", 2);
    }

    public static Meal getMealUpdated() {
        return new Meal(MEAL1_CENTRAL_ID, getMealCentral.getDateMeal().plusDays(2),
                "Updated Meal", 31);
    }
}
