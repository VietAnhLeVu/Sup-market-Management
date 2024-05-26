package com.example.supermarket;

public class productData {
    private final String productID;
    private final String brand;
    private final String productName;
    private final Integer quantity;
    private final Double price;

    public productData(String productID, String brand, String productName, Integer quantity, Double price) {
        this.productID = productID;
        this.brand = brand;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }
}
