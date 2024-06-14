import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection myConnection = null;
//        Statement myStatement = null;
        PreparedStatement myPreparedStatement = null;
        ResultSet myResultSet = null;

        try  {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
            System.out.println("Connect successful");

            String sql = ("INSERT INTO employees (first_name, pa_surname) VALUES (?, ?)");
            myPreparedStatement = myConnection.prepareStatement(sql);
            myPreparedStatement.setString(1, "Carlos");
            myPreparedStatement.setString(2, "Palacios");

            int rowsAffected = myPreparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Create new employee");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Oh no! Something went wrong");
        }
    }
}