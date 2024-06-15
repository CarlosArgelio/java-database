package org.example.platzi.main;

import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (
                Connection myConnection = DatabaseConnection.getInstance();
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