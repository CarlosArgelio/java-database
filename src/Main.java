import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try  {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
            System.out.println("Connect successful");

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