package org.voting;

import org.voting.entity.MenuItem;
import org.voting.to.MenuItemTo;

import java.util.List;

import static org.voting.entity.abstractEntity.AbstractBaseEntity.START_SEQ;
import static org.voting.util.MenuItemsUtil.convertToMenuItemTo;

public class MenuItemTestData {
    public static final MatcherFactory.Matcher<MenuItem> MENU_ITEM_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant");
    public static final MatcherFactory.Matcher<MenuItemTo> MENU_ITEM_TO_MATCHER = MatcherFactory.usingEqualsComparator(MenuItemTo.class);

    public static final int NOT_FOUND = 10;
    public static final int MENU_ITEM1_CENTRAL_ID = START_SEQ + 7;
    public static final int MENU_ITEM1_ATELIERCRENN_ID = START_SEQ + 13;

    public static final MenuItem GET_MENU_ITEM_CENTRAL = new MenuItem(MENU_ITEM1_CENTRAL_ID,
            "Marine Soil (-20 M)", 30);
    public static final MenuItemTo menuItemCentral1 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_CENTRAL_ID,
            "Marine Soil (-20 M)", 30));
    public static final MenuItemTo menuItemCentral2 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_CENTRAL_ID + 1,
            "Low Andes Mountain (1800 M)", 44));
    public static final MenuItemTo menuItemCentral3 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_CENTRAL_ID + 2,
            "Extreme Stem (2875 M)", 26));
    public static final MenuItemTo menuItemAtelierCrenn1 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_ATELIERCRENN_ID,
            "Daisy Flower", 33));
    public static final MenuItemTo menuItemAtelierCrenn2 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_ATELIERCRENN_ID + 1,
            "Love to Eat Avocado Toast", 68));
    public static final MenuItemTo menuItemAtelierCrenn3 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_ATELIERCRENN_ID + 2,
            "Shining star", 49));
    public static final MenuItemTo menuItemAtelierCrenn4 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_ATELIERCRENN_ID + 3,
            "Some test meal in past", 1));
    public static final MenuItemTo menuItemAtelierCrenn5 = convertToMenuItemTo(new MenuItem(MENU_ITEM1_ATELIERCRENN_ID + 4,
            "Second test meal in past", 1));

    public static final List<MenuItemTo> menuItemsToCentralForToday = List.of(menuItemCentral1, menuItemCentral2, menuItemCentral3);

    public static MenuItem getNew() {
        return new MenuItem(null, "New MenuItem", 2);
    }

    public static MenuItem getMenuItemUpdated() {
        return new MenuItem(MENU_ITEM1_CENTRAL_ID, GET_MENU_ITEM_CENTRAL.getDateMenuItem().plusDays(2),
                "Updated MenuItem", 31);
    }
}
