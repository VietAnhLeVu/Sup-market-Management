package com.example.supermarket;

import java.sql.*;

public class SQLconnect {

    public static Connection connectTodb() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/",
                "root",
                "Mastermaster123"
        );
    }
}
