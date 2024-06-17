package org.example.platzi.main;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection myConnection = DatabaseConnection.getInstance()) {

            if ( myConnection.getAutoCommit() ) {
                myConnection.setAutoCommit((false));
            }

            try {
                Repository<Employee> repository = new EmployeeRepository(myConnection);

                System.out.println("--------Insert new client---------");
                Employee employee = new Employee();
                /* employee.setFirst_name("America");
                employee.setPa_surname("Lopez");
                employee.setMa_surname("Villanueva");
                employee.setEmail("alopezv@mail.com");
                employee.setSalary((float)300);
                employee.setCurp("AMERT12345IU567IOS");
                repository.save(employee);
                myConnection.commit(); */

                employee.setFirst_name("David2");
                employee.setPa_surname("Ramos");
                employee.setMa_surname("Del mar");
                employee.setEmail("dramosdm@mail.com");
                employee.setSalary((float)500);
                employee.setCurp("AMERT12345IU567IOS");
                repository.save(employee);
                myConnection.commit();


            } catch (SQLException e) {
                myConnection.rollback();
                throw new RuntimeException(e);
            }

        }
    }
}