package org.voting.to;

import org.voting.entity.Restaurant;

import java.util.List;

public class RestaurantTo {
    private final Integer id;
    private final String name;
    private final List<MenuItemTo> menuItemTos;

    public RestaurantTo(Restaurant restaurant, List<MenuItemTo> menuItemTos) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.menuItemTos = menuItemTos;
    }
}
