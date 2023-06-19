package api;

import java.util.Date;

public class Product {

    private double price;
    private String name;
    private long quantity;

    private Date expirationData;

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
}
