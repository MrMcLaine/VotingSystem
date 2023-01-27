package org.voting.to;

import java.time.LocalDate;
import java.util.Objects;

public class MenuItemTo {
    private final Integer id;
    private final LocalDate dateMenuItem;
    private final String description;
    private final Integer price;

    public MenuItemTo(Integer id, LocalDate dateMenuItem, String description, Integer price) {
        this.id = id;
        this.dateMenuItem = dateMenuItem;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemTo menuItemTo)) return false;

        if (!Objects.equals(id, menuItemTo.id)) return false;
        if (!Objects.equals(dateMenuItem, menuItemTo.dateMenuItem)) return false;
        if (!Objects.equals(description, menuItemTo.description)) return false;
        return Objects.equals(price, menuItemTo.price);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateMenuItem != null ? dateMenuItem.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
