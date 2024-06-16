package org.example.platzi.main;

import org.example.platzi.model.Employee;
import org.example.platzi.repository.EmployeeRepository;
import org.example.platzi.repository.Repository;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection myConnection = DatabaseConnection.getInstance()) {
            Repository<Employee> repository = new EmployeeRepository();

            System.out.println("List all");
            repository.findAll().forEach(System.out::println);

            System.out.println("------Insert Employee------");
            Employee employee = new Employee();
            employee.setFirst_name("Carlos");
            employee.setPa_surname("Palacios");
            employee.setMa_surname("Ramos");
            employee.setEmail("carlosargelio0104@mail.com");
            employee.setSalary((float)100.54);
            repository.save(employee);

            System.out.println("List all two");
            repository.findAll().forEach(System.out::println);
        }
    }
}