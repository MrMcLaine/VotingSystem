package org.voting.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.voting.entity.MenuItem;
import org.voting.entity.Restaurant;
import org.voting.repository.CrudMenuItemRepository;
import org.voting.repository.CrudRestaurantRepository;
import org.voting.repository.MenuItemRepository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class MenuItemRepositoryImpl implements MenuItemRepository {
    @Autowired
    private CrudMenuItemRepository crudMenuItemRepository;
    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public MenuItem get(int id, int restaurantId) {
        return crudMenuItemRepository
                .findById(id)
                .filter(menuItem -> menuItem.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    @Transactional
    public MenuItem save(MenuItem menuItem, int restaurantId) {
        if (!menuItem.isNew() && get(menuItem.id(), restaurantId) == null) {
            return null;
        }
        Restaurant t = (crudRestaurantRepository.get(restaurantId));
        menuItem.setRestaurant(t);
        return crudMenuItemRepository.save(menuItem);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMenuItemRepository.delete(id, restaurantId) != 0;
    }


    @Override
    public List<MenuItem> getMenuForDate(int restaurantId, LocalDate date) {
        return crudMenuItemRepository.getMenuForDate(restaurantId, date);
    }

    @Override
    public List<MenuItem> getHistoryOfMenu(int restaurantId) {
        return crudMenuItemRepository.getHistoryOfMenu(restaurantId);
    }
}
