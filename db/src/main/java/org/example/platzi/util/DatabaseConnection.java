package org.example.platzi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/project";
    private static String user = "root";
    private static String password = "123456";
    private static Connection myConnection;

    public static Connection getInstance() throws SQLException {
        if ( myConnection == null ) myConnection = DriverManager.getConnection(url, user, password);
        return myConnection;
    }
}
