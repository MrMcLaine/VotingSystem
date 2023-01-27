package org.voting.util;

import org.voting.entity.MenuItem;
import org.voting.to.MenuItemTo;

import java.util.List;
import java.util.stream.Collectors;

public class MenuItemsUtil {
    public static List<MenuItemTo> convertListToMenuItemTo(List<MenuItem> menuItems) {
/*        List<MenuItemTo> resultList = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            resultList.add(convertToMenuItemTo(menuItem));
        }
        return resultList;*/
        return menuItems.stream()
                .map(MenuItemsUtil::convertToMenuItemTo)
                .collect(Collectors.toList());
    }

    public static MenuItemTo convertToMenuItemTo(MenuItem menuItem) {
        return new MenuItemTo(menuItem.getId(), menuItem.getDateMenuItem(),
                menuItem.getDescription(), menuItem.getPrice());
    }
}
