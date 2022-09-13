package ru.gritandrey.restaurantvotingsystem.util;

import lombok.experimental.UtilityClass;
import ru.gritandrey.restaurantvotingsystem.model.MenuItem;
import ru.gritandrey.restaurantvotingsystem.to.MenuItemTo;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class MenuItemUtil {

    public static MenuItemTo getTo(MenuItem menuItem) {
        return MenuItemTo.builder()
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .id(menuItem.getId())
                .build();
    }

    public static List<MenuItemTo> getTos(Collection<MenuItem> menuItems) {
        return menuItems.stream().map(MenuItemUtil::getTo).collect(toList());
    }
}
