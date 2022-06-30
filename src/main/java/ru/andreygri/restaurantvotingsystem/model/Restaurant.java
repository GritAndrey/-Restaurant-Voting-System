package ru.andreygri.restaurantvotingsystem.model;

public class Restaurant extends AbstractNamedEntity {
    private String address;

    public Restaurant(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
