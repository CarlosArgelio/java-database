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

            String sql = ("DELETE FROM employees WHERE first_name = 'Carlos'");
            myStatement = myConnection.createStatement();
            int rowsAffected = myStatement.executeUpdate(sql);

            myResultSet =  myStatement.executeQuery("SELECT * FROM employees ORDER BY pa_surname");
            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name") + "," + myResultSet.getString("email"));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            System.out.println("Oh no! Something went wrong");
        }
    }
}