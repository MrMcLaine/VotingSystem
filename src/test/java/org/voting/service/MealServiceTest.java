package org.voting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.voting.entity.Meal;
import org.voting.to.MealTo;
import org.voting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.voting.MealTestData.*;
import static org.voting.RestaurantTestData.BONBON_ID;
import static org.voting.RestaurantTestData.CENTRAL_ID;

class MealServiceTest extends AbstractServiceTest {

    @Autowired
    protected MealService service;

    @Test
    void get() {
        MealTo actual = service.get(MEAL1_CENTRAL_ID, CENTRAL_ID);
        Assertions.assertEquals(actual, mealCentral1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, CENTRAL_ID));
    }

    @Test
    void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_CENTRAL_ID, BONBON_ID));
    }

    @Test
    void create() {
        Meal created = service.create(getNew(), CENTRAL_ID);
        int newId = created.id();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
    }

    @Test
    void update() {
        Meal updated = getMealUpdated();
        service.update(updated, CENTRAL_ID);
        Assertions.assertEquals(service.get(MEAL1_CENTRAL_ID, CENTRAL_ID), new MealTo(getMealUpdated()));
    }

    @Test
    void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(getMealUpdated(), BONBON_ID));
        Assertions.assertEquals("Not found entity with id=" + BONBON_ID, exception.getMessage());
    }

    @Test
    void delete() {
        service.delete(MEAL1_CENTRAL_ID, CENTRAL_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_CENTRAL_ID, CENTRAL_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, CENTRAL_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL1_CENTRAL_ID, BONBON_ID));
    }

    @Test
    void getActualMenu() {
        List<MealTo> actualMenu=service.getMenuForDate(CENTRAL_ID, LocalDate.now());
        Assertions.assertEquals(actualMenu,mealsToCentralForToday);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Meal(null, "", 33), CENTRAL_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Meal(null, "Test meal  ", 0 ), CENTRAL_ID));
    }
}