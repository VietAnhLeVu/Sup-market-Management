package com.example.supermarket;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

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

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public ObjectProperty<Integer> customerIdProperty() {
        return customerId;
    }

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public ObjectProperty<String> brandProperty() {
        return brand;
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public ObjectProperty<String> productNameProperty() {
        return productName;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public ObjectProperty<Integer> quantityProperty() {
        return quantity;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public ObjectProperty<Double> priceProperty() {
        return price;
    }

    public Date getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }
}
