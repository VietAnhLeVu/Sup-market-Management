package com.example.supermarket;

public class productData {
    private String productID;
    private String brand;
    private String productName;
    private Integer quantity;
    private Double price;

    public productData(String productID,String brand,String productName,Integer quantity,Double price) {
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
