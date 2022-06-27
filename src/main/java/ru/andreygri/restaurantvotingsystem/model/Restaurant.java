package ru.andreygri.restaurantvotingsystem.model;

import java.util.Set;

public class Restaurant extends AbstractNamedEntity {
    private Set<Menu> menus;

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant.getId(), restaurant.getName());
        this.menus = restaurant.getMenus();
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
