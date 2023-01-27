package org.voting.web.controller.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import org.voting.entity.MenuItem;
import org.voting.service.MenuItemService;
import org.voting.to.MenuItemTo;
import org.voting.util.exception.NotFoundException;
import org.voting.web.AbstractControllerTest;
import org.voting.web.json.JsonUtil;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.voting.MenuItemTestData.*;
import static org.voting.MenuItemTestData.NOT_FOUND;
import static org.voting.RestaurantTestData.CENTRAL_ID;
import static org.voting.TestUtil.userHttpBasic;
import static org.voting.UserTestData.ADMIN;
import static org.voting.util.MenuItemsUtil.convertToMenuItemTo;


public class MenuItemRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuItemRestController.REST_URL + "/";

    @Autowired
    private MenuItemService service;

    @Test
    public void createWithLocations() throws Exception {
        MenuItem newMenuItem = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL, CENTRAL_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(newMenuItem)))
                .andExpect(status().isCreated());

        MenuItem created = MENU_ITEM_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenuItem.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newMenuItem);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU_ITEM1_CENTRAL_ID, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> service.get(MENU_ITEM1_CENTRAL_ID, CENTRAL_ID));
    }

    @Test
    void update() throws Exception {
        MenuItemTo updated = convertToMenuItemTo(getMenuItemUpdated());
        perform(MockMvcRequestBuilders.put(REST_URL + MENU_ITEM1_CENTRAL_ID, CENTRAL_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        MENU_ITEM_TO_MATCHER.assertMatch(service.get(MENU_ITEM1_CENTRAL_ID, CENTRAL_ID), updated);
    }

    @Test
    void getActualMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());
        MENU_ITEM_TO_MATCHER.assertMatch(service.getMenuForDate(CENTRAL_ID, LocalDate.now()), menuItemsToCentralForToday);
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_ITEM1_CENTRAL_ID, CENTRAL_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_ITEM_MATCHER.contentJson(GET_MENU_ITEM_CENTRAL));
    }

    @Test
    void getNotFound() {
        Exception exception = assertThrows((NestedServletException.class), () ->
                perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND, CENTRAL_ID)
                        .with(userHttpBasic(ADMIN)))
                        .andDo(print())
                        .andExpect(status().isNotFound()));
        String expectedMessage = "Not found entity with id=10";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}