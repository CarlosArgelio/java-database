package org.example.platzi.repository;

import org.example.platzi.model.Employee;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Statement myStatement = getConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM employees");) {

            while( myResultSet.next() ) {
                Employee e = createEmployee(myResultSet);
                employees.add(e);
            }
        }
        return  employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try (PreparedStatement myStatement = getConnection().prepareStatement("SELECT  * FROM employees WHERE id = ?");) {
            myStatement.setInt(1, id);
            try(ResultSet myResultSet = myStatement.executeQuery()) {
                if ( myResultSet.next() ) employee = createEmployee(myResultSet);
            }
        }
        return  employee;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?,?,?,?,?)";
        try (PreparedStatement myStatement = getConnection().prepareStatement(sql)) {
            myStatement.setString(1, employee.getFirst_name());
            myStatement.setString(2, employee.getPa_surname());
            myStatement.setString(3, employee.getMa_surname());
            myStatement.setString(4, employee.getEmail());
            myStatement.setFloat(5, employee.getSalary());
            myStatement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) {

    }

    private Employee createEmployee(ResultSet myResultSet) throws SQLException {
        Employee e = new Employee();
        e.setId(myResultSet.getInt("id"));
        e.setFirst_name(myResultSet.getString("first_name"));
        e.setPa_surname(myResultSet.getString("pa_surname"));
        e.setMa_surname(myResultSet.getString("ma_surname"));
        e.setEmail(myResultSet.getString("email"));
        e.setSalary(myResultSet.getFloat("salary"));

        return e;
    }
}
