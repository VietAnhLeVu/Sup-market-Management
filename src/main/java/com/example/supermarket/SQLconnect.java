package com.example.supermarket;

import java.net.ConnectException;
import java.sql.*;

public class SQLconnect {

    public static Connection connectTodb() throws SQLException {
        Connection connect =  DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/",
                "root",
                "Mastermaster123"
        );

        Statement use = connect.createStatement();
        use.execute("USE classicmodels");
        return connect;
    }
}
