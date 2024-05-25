package com.example.supermarket;

import javafx.beans.property.*;

import java.sql.Date;

public final class PurchaseData {
    private final ObjectProperty<Integer> customerId = new SimpleObjectProperty<>();
    private final ObjectProperty<String> brand = new SimpleObjectProperty<>();
    private final ObjectProperty<String> productName = new SimpleObjectProperty<>();
    private final ObjectProperty<Integer> quantity = new SimpleObjectProperty<>();
    private final ObjectProperty<Double> price = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> date = new SimpleObjectProperty<>();

    public int getCustomerId() {
        return customerId.get();
    }

    public ObjectProperty<Integer> customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getBrand() {
        return brand.get();
    }

    public ObjectProperty<String> brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getProductName() {
        return productName.get();
    }

    public ObjectProperty<String> productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public ObjectProperty<Integer> quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getPrice() {
        return price.get();
    }

    public ObjectProperty<Double> priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }
}
