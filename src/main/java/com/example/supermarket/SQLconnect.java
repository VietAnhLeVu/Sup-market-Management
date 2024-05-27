package com.example.supermarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            use.execute("USE quan_ly_ban_hang");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }
}
