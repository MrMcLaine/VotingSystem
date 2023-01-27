package org.voting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.voting.entity.MenuItem;
import org.voting.to.MenuItemTo;
import org.voting.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.voting.MenuItemTestData.*;
import static org.voting.RestaurantTestData.BONBON_ID;
import static org.voting.RestaurantTestData.CENTRAL_ID;
import static org.voting.util.MenuItemsUtil.convertToMenuItemTo;

class MenuItemServiceTest extends AbstractServiceTest {

    @Autowired
    protected MenuItemService service;

    @Test
    void get() {
        MenuItemTo actual = service.get(MENU_ITEM1_CENTRAL_ID, CENTRAL_ID);
        Assertions.assertEquals(actual, menuItemCentral1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, CENTRAL_ID));
    }

    @Test
    void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(MENU_ITEM1_CENTRAL_ID, BONBON_ID));
    }

    @Test
    void create() {
        MenuItem created = service.create(getNew(), CENTRAL_ID);
        int newId = created.id();
        MenuItem newMenuItem = getNew();
        newMenuItem.setId(newId);
        MENU_ITEM_MATCHER.assertMatch(created, newMenuItem);
    }

    @Test
    void update() {
        MenuItem updated = getMenuItemUpdated();
        service.update(updated, CENTRAL_ID);
        Assertions.assertEquals(service.get(MENU_ITEM1_CENTRAL_ID, CENTRAL_ID), convertToMenuItemTo(getMenuItemUpdated()));
    }

    @Test
    void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                service.update(getMenuItemUpdated(), BONBON_ID));
        Assertions.assertEquals("Not found entity with id=" + BONBON_ID, exception.getMessage());
    }

    @Test
    void delete() {
        service.delete(MENU_ITEM1_CENTRAL_ID, CENTRAL_ID);
        assertThrows(NotFoundException.class, () -> service.get(MENU_ITEM1_CENTRAL_ID, CENTRAL_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, CENTRAL_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(MENU_ITEM1_CENTRAL_ID, BONBON_ID));
    }

    @Test
    void getActualMenu() {
        List<MenuItemTo> actualMenu = service.getMenuForDate(CENTRAL_ID, LocalDate.now());
        Assertions.assertEquals(actualMenu, menuItemsToCentralForToday);
    }

    @Test
    void createWithException() {
        validateRootCause(ConstraintViolationException.class, () ->
                service.create(new MenuItem(null, "", 33), CENTRAL_ID));
        validateRootCause(ConstraintViolationException.class, () ->
                service.create(new MenuItem(null, "Test meal  ", 0), CENTRAL_ID));
    }
}