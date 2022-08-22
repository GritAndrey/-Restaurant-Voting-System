package ru.gritandrey.restaurantvotingsystem;


import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.to.DishTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantTo;
import ru.gritandrey.restaurantvotingsystem.util.mapper.DishMapper;
import ru.gritandrey.restaurantvotingsystem.util.mapper.RestaurantMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static ru.gritandrey.restaurantvotingsystem.model.BaseEntity.START_SEQ;

public class RestaurantAndDishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant", "food");
    public static final MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(DishTo.class);
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");
    public static final MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class);

    public static final LocalDate TODAY = LocalDate.now();
    public static final int NOT_FOUND = 66666;
    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int RESTAURANT2_ID = START_SEQ + 1;
    public static final int RESTAURANT3_ID = START_SEQ + 2;
    public static final int RESTAURANT4_ID = START_SEQ + 3;
    public static final int DISH1_ID = RESTAURANT4_ID + 1;
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
    public static final String newFood = "New food";

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Rau LLC", "1 Stone Corner Junction");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Sporer-Parisian", "16 Forest Junction");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT3_ID, "Kon and Sons", "4916 Kim Street");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT4_ID, "Bechtelar Group", "3 Dorton Court");

    public static final Dish dish1 = new Dish(DISH1_ID, new BigDecimal("12.300"), food1, restaurant1, TODAY);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, new BigDecimal("4.600"), food2, restaurant1, TODAY);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, new BigDecimal("3.500"), food3, restaurant1, TODAY);

    public static final Dish dish4 = new Dish(DISH1_ID + 3, new BigDecimal("35.120"), food4, restaurant2, TODAY);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, new BigDecimal("12.500"), food5, restaurant2, TODAY);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, new BigDecimal("2.100"), food6, restaurant2, TODAY);

    public static final Dish dish7 = new Dish(DISH1_ID + 6, new BigDecimal("0.300"), food7, restaurant3, TODAY);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, new BigDecimal("4.500"), food8, restaurant3, TODAY);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, new BigDecimal("6.400"), food9, restaurant3, TODAY);

    public static final Dish dish10 = new Dish(DISH1_ID + 9, new BigDecimal("2.800"), food10, restaurant4, TODAY);
    public static final Dish dish11 = new Dish(DISH1_ID + 10, new BigDecimal("22.500"), food4, restaurant4, TODAY);
    public static final Dish dish12 = new Dish(DISH1_ID + 11, new BigDecimal("33.100"), food5, restaurant4, TODAY);


    public static final DishTo dish1To = DishTo.builder()
            .id(dish1.getId())
            .price(dish1.getPrice())
            .name(dish1.getName())
            .restaurantId(dish1.getRestaurant().getId())
            .build();
    public static final List<Dish> dishes = List.of(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10, dish11, dish12);
    public static final List<RestaurantTo> restaurantsTo;
    public static final List<RestaurantTo> restaurantsToNoMenu;
    public static final RestaurantTo restaurant1ToWithMenu;
    public static final RestaurantTo restaurant1ToWithoutMenu;

    static {
        restaurant1ToWithoutMenu = RestaurantMapper.getTo(restaurant1);
        restaurantsToNoMenu = Stream.of(restaurant1, restaurant2, restaurant3, restaurant4)
                .map(RestaurantMapper::getTo)
                .collect(toList());
        restaurant1.setMenu(List.of(dish1, dish2, dish3));
        restaurant2.setMenu(List.of(dish4, dish5, dish6));
        restaurant3.setMenu(List.of(dish7, dish8, dish9));
        restaurant4.setMenu(List.of(dish10, dish11, dish12));
        restaurant1ToWithMenu = RestaurantMapper.getTo(restaurant1);
        restaurantsTo = Stream.of(restaurant1, restaurant2, restaurant3, restaurant4)
                .map(RestaurantMapper::getTo)
                .collect(toList());
    }

    public static Dish getNewDishWithExistingNameAndRestaurant() {
        return new Dish(null, new BigDecimal("33.1"), food10, restaurant1, TODAY);
    }

    public static DishTo getNewDishTo() {
        final var newDish = getNewDishWithExistingNameAndRestaurant();
        return DishMapper.getTo(newDish);
    }

    public static Dish getNewDishWithNewNameAndRestaurant() {
        return new Dish(null, new BigDecimal("33.100"), newFood, restaurant1, TODAY);
    }

    public static Dish getUpdatedDish() {
        return new Dish(DISH1_ID, new BigDecimal("12.300"), food1, restaurant1, TODAY);
    }

    public static Restaurant getNewRestaurant() {
        return new Restaurant(null, "New rest name", "New rest address");
    }

    public static RestaurantTo getNewRestaurantTo() {
        return RestaurantMapper.getTo(getNewRestaurant());
    }

    public static Restaurant getUpdatedRestaurant() {
        return new Restaurant(RESTAURANT1_ID, "updated name", "updated address");
    }
}
