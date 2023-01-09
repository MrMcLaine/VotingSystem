package org.voting.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.voting.entity.Meal;
import org.voting.service.MealService;
import org.voting.util.exception.NotFoundException;
import org.voting.web.AbstractControllerTest;
import org.voting.web.json.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.voting.MealTestData.*;
import static org.voting.MealTestData.NOT_FOUND;
import static org.voting.RestaurantTestData.CENTRAL_ID;
import static org.voting.TestUtil.userHttpBasic;
import static org.voting.UserTestData.ADMIN;


public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + "/";

    @Autowired
    private MealService service;

    @Test
    public void createWithLocations() throws Exception {
        Meal newMeal = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL, CENTRAL_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(newMeal)))
                .andExpect(status().isCreated());

        Meal created = MEAL_MATCHER.readFromJson(action);
        int newId = created.id();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(service.getActualMenu(CENTRAL_ID), created, mealCentral1, mealCentral2, mealCentral3);
    }

    @Test
    public void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MEAL1_CENTRAL_ID, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(MEAL1_CENTRAL_ID, CENTRAL_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MEAL1_CENTRAL_ID, CENTRAL_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MEAL_MATCHER.assertMatch(service.get(MEAL1_CENTRAL_ID, CENTRAL_ID), updated);
    }

    @Test
    public void getActualMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        MEAL_MATCHER.assertMatch(service.getActualMenu(CENTRAL_ID), mealsCentralForToday);
    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MEAL1_CENTRAL_ID, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(mealCentral1));
    }

    @Test
    public void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, CENTRAL_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}