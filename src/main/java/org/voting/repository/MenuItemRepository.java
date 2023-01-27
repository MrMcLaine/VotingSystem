package org.voting.repository;

import org.voting.entity.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository {
    MenuItem get(int id, int restaurantId);

    MenuItem save(MenuItem menuItem, int restaurantId);

    boolean delete(int id, int restaurantId);

    List<MenuItem> getMenuForDate(int restaurantId, LocalDate date);

    List<MenuItem> getHistoryOfMenu(int restaurantId);
}
