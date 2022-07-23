package ru.gritandrey.restaurantvotingsystem;


import ru.gritandrey.restaurantvotingsystem.model.Dish;
import ru.gritandrey.restaurantvotingsystem.model.Restaurant;
import ru.gritandrey.restaurantvotingsystem.model.RestaurantDish;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantDishTo;
import ru.gritandrey.restaurantvotingsystem.to.RestaurantWithMenuTo;
import ru.gritandrey.restaurantvotingsystem.util.builder.ToBuilderUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import static ru.gritandrey.restaurantvotingsystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantAndDishTestData {
    public static final MatcherFactory.Matcher<RestaurantDish> RESTAURANT_DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantDish.class, "dish");
    public static final MatcherFactory.Matcher<RestaurantDishTo> RESTAURANT_DISH_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantDishTo.class);
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");
    public static final MatcherFactory.Matcher<RestaurantWithMenuTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantWithMenuTo.class, "menu");

    public static final LocalDate TODAY = LocalDate.now();
    public static final int NOT_FOUND = 66666;
    public static final int RESTAURANT_1_ID = START_SEQ;
    public static final int RESTAURANT_2_ID = START_SEQ + 1;
    public static final int RESTAURANT_3_ID = START_SEQ + 2;
    public static final int RESTAURANT_4_ID = START_SEQ + 3;
    public static final int DISH1_ID = RESTAURANT_4_ID + 1;
    public static final int RESTAURANT_DISH1_ID = DISH1_ID + 10; //100014

    public static final Dish dish1 = new Dish(DISH1_ID,"California Suncup");
    public static final Dish dish2 = new Dish(DISH1_ID,"Uluhe");
    public static final Dish dish3 = new Dish(DISH1_ID +1,"Wild Sweetwilliam");
    public static final Dish dish4 = new Dish(DISH1_ID+2,"Smallhead Cat's Ear");
    public static final Dish dish5 = new Dish(DISH1_ID+3,"West Indian Mahogany");
    public static final Dish dish6 = new Dish(DISH1_ID+4,"Plains Blackberry");
    public static final Dish dish7 = new Dish(DISH1_ID+5,"Narrow-petal Rein Orchid");
    public static final Dish dish8 = new Dish(DISH1_ID+6,"Oryctes");
    public static final Dish dish9 = new Dish(DISH1_ID+7,"Horsetail");
    public static final Dish dish10 = new Dish(DISH1_ID+8,"Goldback Fern");

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_1_ID, "Rau LLC", "1 Stone Corner Junction");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_2_ID, "Sporer-Parisian", "16 Forest Junction");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_3_ID, "Kon and Sons", "4916 Kim Street");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT_4_ID, "Bechtelar Group", "3 Dorton Court");

    public static final RestaurantDish restaurantDish1 = new RestaurantDish(RESTAURANT_DISH1_ID,new BigDecimal("12.3"),dish1,restaurant1, TODAY);
    public static final RestaurantDish restaurantDish2 = new RestaurantDish(RESTAURANT_DISH1_ID+1,new BigDecimal("4.6"),dish2,restaurant1, TODAY);
    public static final RestaurantDish restaurantDish3 = new RestaurantDish(RESTAURANT_DISH1_ID+2,new BigDecimal("3.5"),dish3,restaurant1, TODAY);
    public static final RestaurantDish restaurantDish4 = new RestaurantDish(RESTAURANT_DISH1_ID+3,new BigDecimal("35.12"),dish4,restaurant2, TODAY);
    public static final RestaurantDish restaurantDish5 = new RestaurantDish(RESTAURANT_DISH1_ID+4,new BigDecimal("12.5"),dish5,restaurant2, TODAY);
    public static final RestaurantDish restaurantDish6 = new RestaurantDish(RESTAURANT_DISH1_ID+5,new BigDecimal("2.1"),dish6,restaurant2, TODAY);
    public static final RestaurantDish restaurantDish7 = new RestaurantDish(RESTAURANT_DISH1_ID+6,new BigDecimal("0.3"),dish7,restaurant3, TODAY);
    public static final RestaurantDish restaurantDish8 = new RestaurantDish(RESTAURANT_DISH1_ID+7,new BigDecimal("4.5"),dish8,restaurant3, TODAY);
    public static final RestaurantDish restaurantDish9 = new RestaurantDish(RESTAURANT_DISH1_ID+8,new BigDecimal("6.4"),dish9,restaurant3, TODAY);
    public static final RestaurantDish restaurantDish10 = new RestaurantDish(RESTAURANT_DISH1_ID+9,new BigDecimal("2.8"),dish10,restaurant4, TODAY);
    public static final RestaurantDish restaurantDish11 = new RestaurantDish(RESTAURANT_DISH1_ID+10,new BigDecimal("22.5"),dish4,restaurant4, TODAY);
    public static final RestaurantDish restaurantDish12 = new RestaurantDish(RESTAURANT_DISH1_ID+11,new BigDecimal("33.1"),dish5,restaurant4, TODAY);

    public static final List<RestaurantDish> restaurantDishes = List.of(restaurantDish1,restaurantDish2,restaurantDish3,restaurantDish4,restaurantDish5,restaurantDish6,restaurantDish7,restaurantDish8,restaurantDish9,restaurantDish10,restaurantDish11,restaurantDish12);
    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3, restaurant4);
    public static final List<RestaurantWithMenuTo> restaurantsTo;
    public static final RestaurantWithMenuTo restaurantWithMenuTo;

    static {
        restaurant1.setMenu(List.of(restaurantDish1, restaurantDish2, restaurantDish3));
        restaurant2.setMenu(List.of(restaurantDish4, restaurantDish5, restaurantDish6));
        restaurant3.setMenu(List.of(restaurantDish7, restaurantDish8, restaurantDish9));
        restaurant4.setMenu(List.of(restaurantDish10, restaurantDish11, restaurantDish12));
        restaurantsTo = Stream.of(restaurant1, restaurant2, restaurant3, restaurant4).map(ToBuilderUtil::getRestaurantWithMenuTo)
                .collect(toList());
        restaurantWithMenuTo = ToBuilderUtil.getRestaurantWithMenuTo(restaurant1);
    }
}
