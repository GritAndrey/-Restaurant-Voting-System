package ru.gritandrey.restaurantvotingsystem;


import ru.gritandrey.restaurantvotingsystem.model.MenuItem;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.to.MenuItemTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.util.RestaurantUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static ru.gritandrey.restaurantvotingsystem.model.BaseEntity.START_SEQ;

public class RestaurantAndDishTestData {
    public static final MatcherFactory.Matcher<MenuItem> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItem.class, "restaurant", "food");
    public static final MatcherFactory.Matcher<MenuItemTo> DISH_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(MenuItemTo.class);
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);

    public static final LocalDate TODAY = LocalDate.now();
    public static final int NOT_FOUND = 66666;
    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int RESTAURANT2_ID = START_SEQ + 1;
    public static final int RESTAURANT3_ID = START_SEQ + 2;
    public static final int RESTAURANT4_ID = START_SEQ + 3;
    public static final int DISH1_ID = START_SEQ;
    public static final String food1 = "California Suncup";
    public static final String food2 = "Uluhe";
    public static final String food3 = "Wild Sweetwilliam";
    public static final String food4 = "Smallhead Cat's Ear";
    public static final String food5 = "West Indian Mahogany";
    public static final String food6 = "Plains Blackberry";
    public static final String food7 = "Narrow-petal Rein Orchid";
    public static final String food8 = "Oryctes";
    public static final String food9 = "Horsetail";
    public static final String food10 = "Goldback Fern";

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Rau LLC", "1 Stone Corner Junction");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Sporer-Parisian", "16 Forest Junction");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "Kon and Sons", "4916 Kim Street");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT4_ID, "Bechtelar Group", "3 Dorton Court");

    public static final MenuItem MENU_ITEM_1 = new MenuItem(DISH1_ID, new BigDecimal("12.300"), food1, restaurant1, TODAY);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(DISH1_ID + 1, new BigDecimal("4.600"), food2, restaurant1, TODAY);
    public static final MenuItem MENU_ITEM_3 = new MenuItem(DISH1_ID + 2, new BigDecimal("3.500"), food3, restaurant1, TODAY);

    public static final MenuItem MENU_ITEM_4 = new MenuItem(DISH1_ID + 3, new BigDecimal("35.120"), food4, restaurant2, TODAY);
    public static final MenuItem MENU_ITEM_5 = new MenuItem(DISH1_ID + 4, new BigDecimal("12.500"), food5, restaurant2, TODAY);
    public static final MenuItem MENU_ITEM_6 = new MenuItem(DISH1_ID + 5, new BigDecimal("2.100"), food6, restaurant2, TODAY);

    public static final MenuItem MENU_ITEM_7 = new MenuItem(DISH1_ID + 6, new BigDecimal("0.300"), food7, restaurant3, TODAY);
    public static final MenuItem MENU_ITEM_8 = new MenuItem(DISH1_ID + 7, new BigDecimal("4.500"), food8, restaurant3, TODAY);
    public static final MenuItem MENU_ITEM_9 = new MenuItem(DISH1_ID + 8, new BigDecimal("6.400"), food9, restaurant3, TODAY);

    public static final MenuItem MENU_ITEM_10 = new MenuItem(DISH1_ID + 9, new BigDecimal("2.800"), food10, restaurant4, TODAY);
    public static final MenuItem MENU_ITEM_11 = new MenuItem(DISH1_ID + 10, new BigDecimal("22.500"), food4, restaurant4, TODAY);
    public static final MenuItem MENU_ITEM_12 = new MenuItem(DISH1_ID + 11, new BigDecimal("33.100"), food5, restaurant4, TODAY);


    public static final MenuItemTo dish1To = MenuItemTo.builder()
            .id(MENU_ITEM_1.getId())
            .price(MENU_ITEM_1.getPrice())
            .name(MENU_ITEM_1.getName())
            .build();
    public static final List<MenuItem> MENU_ITEMS = List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9, MENU_ITEM_10, MENU_ITEM_11, MENU_ITEM_12);
    public static final List<RestaurantTo> restaurantsTo;
    public static final List<RestaurantTo> restaurantsToNoMenu;
    public static final RestaurantTo restaurant1ToWithMenu;
    public static final RestaurantTo restaurant1ToWithoutMenu;

    static {
        restaurant1ToWithoutMenu = RestaurantUtil.getTo(restaurant1);
        restaurantsToNoMenu = Stream.of(restaurant1, restaurant2, restaurant3, restaurant4)
                .map(RestaurantUtil::getTo)
                .collect(toList());
        restaurant1.setMenu(List.of(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3));
        restaurant2.setMenu(List.of(MENU_ITEM_4, MENU_ITEM_5, MENU_ITEM_6));
        restaurant3.setMenu(List.of(MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9));
        restaurant4.setMenu(List.of(MENU_ITEM_10, MENU_ITEM_11, MENU_ITEM_12));
        restaurant1ToWithMenu = RestaurantUtil.getTo(restaurant1);
        restaurantsTo = Stream.of(restaurant1, restaurant2, restaurant3, restaurant4)
                .map(RestaurantUtil::getTo)
                .collect(toList());
    }

    public static MenuItem getNewDishWithExistingNameAndRestaurant() {
        return new MenuItem(null, new BigDecimal("33.1"), food10, restaurant1, TODAY);
    }

    public static MenuItem getUpdatedDish() {
        return new MenuItem(DISH1_ID, new BigDecimal("1.300"), "updated", restaurant1, TODAY);
    }

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "New rest name", "New rest address");
    }

    public static RestaurantTo getNewRestaurantTo() {
        return RestaurantUtil.getTo(getNewRestaurant());
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT1_ID, "updated name", "updated address");
    }
}
