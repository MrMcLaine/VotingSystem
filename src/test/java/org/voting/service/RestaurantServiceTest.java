package org.voting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.voting.entity.Restaurant;
import org.voting.util.exception.NotFoundException;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.voting.MealTestData.mealsCentralForToday;
import static org.voting.RestaurantTestData.*;


class RestaurantServiceTest extends AbstractServiceTest{

    @Autowired
    RestaurantService service;

    @Test
    void get() {
        Restaurant restaurant = service.get(CENTRAL_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, central);
    }

    @Test
    void create() {
            Restaurant created = service.create(getNew());
            int newId = created.id();
            Restaurant newRestaurant = getNew();
            newRestaurant.setId(newId);
            RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
            RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "Central, Lima")));
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(CENTRAL_ID), getUpdated());
    }

    @Test
    void delete() {
        service.delete(CENTRAL_ID);
        assertThrows(NotFoundException.class, () -> service.get(CENTRAL_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void getWithMeals() {
        Restaurant centralTest = service.getWithMeals(CENTRAL_ID);
        central.setMeals(new HashSet<>(mealsCentralForToday));
        RESTAURANT_MATCHER.assertMatch(centralTest, central);
    }

    @Test
    void getAll() {
        List<Restaurant> all = service.getAll();
        RESTAURANT_MATCHER.assertMatch(all, central, bonBon, atelierCrenn);
    }

    @Test
    void getWithHistoryOfMeals() {
    }

    @Test
    void getWithDaysHistoryOfMeals() {
    }
}