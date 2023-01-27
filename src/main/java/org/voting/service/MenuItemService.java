package org.voting.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.voting.entity.MenuItem;
import org.voting.repository.MenuItemRepository;
import org.voting.to.MenuItemTo;

import java.time.LocalDate;
import java.util.List;

import static org.voting.util.MenuItemsUtil.convertListToMenuItemTo;
import static org.voting.util.MenuItemsUtil.convertToMenuItemTo;
import static org.voting.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuItemService {
    private final MenuItemRepository repository;

    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItemTo get(int id, int restaurantId) {
        return convertToMenuItemTo(checkNotFoundWithId(repository.get(id, restaurantId), restaurantId));
    }

    public MenuItem create(MenuItem menuItem, int restaurantId) {
        Assert.notNull(menuItem, "menuItem must be not null");
        return repository.save(menuItem, restaurantId);
    }

    public void update(MenuItem menuItem, int restaurantId) {
        Assert.notNull(menuItem, "menuItem must be not null");
        checkNotFoundWithId(repository.save(menuItem, restaurantId), restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public List<MenuItemTo> getMenuForDate(int restaurantId, LocalDate date) {
        return convertListToMenuItemTo(repository.getMenuForDate(restaurantId, date));
    }

    public List<MenuItemTo> getHistoryOfMenu(int restaurantId) {
        return convertListToMenuItemTo(repository.getHistoryOfMenu(restaurantId));
    }

}
