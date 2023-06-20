package api;

import data.Address;

import java.util.Date;

public class Product {

    private double price;
    private String name;
    private long quantity;

    private Date expirationData;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public Date getExpirationData() {
        return expirationData;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setExpirationData(Date expirationData) {
        this.expirationData = expirationData;

    }
}
