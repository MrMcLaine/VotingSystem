package org.voting.util;

import org.voting.entity.Meal;
import org.voting.to.MealTo;

import java.util.ArrayList;
import java.util.List;

public class MealsUtil {
    public static List<MealTo> convertListToMealTo(List<Meal> meals) {
        List<MealTo> resultList = new ArrayList<>();
        for (Meal meal : meals) {
            resultList.add(new MealTo(meal));
        }
        return resultList;
    }
}
