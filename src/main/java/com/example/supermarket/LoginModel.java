package com.example.supermarket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    private static final Connection connection = SQLconnect.connectTodb();

    public static boolean verifyAdminCredentialSQL(String username, String password) {
        try {
            String adminData = "SELECT * FROM admin WHERE username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(adminData);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyEmployeeCredentialSQL(String employeeId, String password) {
        try {
            String employeeData = "SELECT * FROM employees WHERE employeeNumber = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(employeeData);
            preparedStatement.setString(1, employeeId);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
