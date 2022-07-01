package ru.andreygri.restaurantvotingsystem.model;

// :TODO: Think about: RestaurantFood inherits Food. There will be no identical food names in the database.
public class Dish extends AbstractNamedEntity {
    // :TODO:  money https://stackoverflow.com/questions/8148684/what-data-type-to-use-for-money-in-java/43051227#43051227
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
