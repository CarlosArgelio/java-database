package org.example.platzi.main;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try  {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery("SELECT * FROM employees");
            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name"));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Oh no! Something went wrong");
        }
    }
}