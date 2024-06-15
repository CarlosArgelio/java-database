package org.example.platzi.main;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/project";
        String user = "root";
        String password = "123456";

        try (
                Connection myConnection = DriverManager.getConnection(url, user, password);
                Statement myStatement = myConnection.createStatement();
                ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM employees");
        ) {
            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name"));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Oh no! Something went wrong");
        }
    }
}