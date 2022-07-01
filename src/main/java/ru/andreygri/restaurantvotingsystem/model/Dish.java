package ru.andreygri.restaurantvotingsystem.model;

// :TODO: Think about: RestaurantFood inherits Food. There will be no identical food names in the database.
public class Dish extends AbstractNamedEntity {
    private double price;

    protected Dish() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
