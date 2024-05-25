package com.example.supermarket;

import java.net.ConnectException;
import java.sql.*;

public class SQLconnect {

    public static Connection connectTodb() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/",
                    "root",
                    "Mastermaster123"
            );

            Statement use = connect.createStatement();
            use.execute("USE classicmodels");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
