package com.example.supermarket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class EmployeeDashboardDataModel {
    static Connection connection = SQLconnect.connectTodb();

    public static ObservableList<PurchaseData> loadPurchaseListDataSQL() {
        ObservableList<PurchaseData> customerList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM orderData";

        try {
            PurchaseData orderdata;
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                orderdata = new PurchaseData();
                orderdata.setCustomerId(resultSet.getInt("customerId"));
                orderdata.setBrand(resultSet.getString("brand"));
                orderdata.setProductName(resultSet.getString("productName"));
                orderdata.setQuantity(resultSet.getInt("quantity"));
                orderdata.setPrice(resultSet.getDouble("price"));
                orderdata.setDate(resultSet.getDate("date"));
                customerList.add(orderdata);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    public static ObservableList<String> searchBrandSQL(String brand) {
        ObservableList<String> productList = FXCollections.observableArrayList();
        if (brand == null || brand.isEmpty() || brand.isBlank()) return productList;

        String sql = "SELECT productLine, productName, quantityInStock, buyPrice FROM products\n" +
                "WHERE LOWER(productLine) LIKE '%" + brand.toLowerCase() + "%' AND quantityInStock > 0;";

        try {
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                productList.add(resultSet.getString("productName"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return productList;
    }

    public static ObservableList<String> getBrandListSQL() {
        ObservableList<String> brandList = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT productLine FROM products;";

        try {
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            while (resultSet.next()) {
                brandList.add(resultSet.getString("productLine"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return brandList;
    }

    public static void addProductToPurchaseListSQL() {

    }

    public static Double getProductPriceSQL(String productName) {
        String sql = "SELECT buyPrice FROM products WHERE productName = '" + productName + "';";
        try {
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            resultSet.next();
            return resultSet.getDouble("buyPrice");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
