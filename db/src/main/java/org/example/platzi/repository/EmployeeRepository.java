package org.example.platzi.repository;

import org.example.platzi.model.Employee;
import org.example.platzi.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private Connection myConnection;

    public EmployeeRepository(Connection myConnection) {
        this.myConnection = myConnection;
    }
    
    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try(Statement myStatement = myConnection.createStatement();
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
        try (PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM employees WHERE id = ?");) {
            myStatement.setInt(1, id);
            try(ResultSet myResultSet = myStatement.executeQuery()) {
                if ( myResultSet.next() ) employee = createEmployee(myResultSet);
            }
        }
        return  employee;
    }

    @Override
    public void save(Employee employee) {
        String sql;

        if (employee.getId() != null && employee.getId() > 0) sql = "UPDATE employees SET first_name =?, pa_surname =?, ma_surname =?, email =?, salary =?, curp =?";
        else sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary, curp) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement myStatement = myConnection.prepareStatement(sql)) {
            myStatement.setString(1, employee.getFirst_name());
            myStatement.setString(2, employee.getPa_surname());
            myStatement.setString(3, employee.getMa_surname());
            myStatement.setString(4, employee.getEmail());
            myStatement.setFloat(5, employee.getSalary());
            myStatement.setString(6, employee.getCurp());

            if (employee.getId() != null && employee.getId() > 0) myStatement.setInt(7, employee.getId());

            myStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement myStatement = myConnection.prepareStatement("DELETE FROM employees WHERE id =?")) {
            myStatement.setInt(1, id);
            myStatement.executeUpdate();
        }
    }

    private Employee createEmployee(ResultSet myResultSet) throws SQLException {
        Employee e = new Employee();
        e.setId(myResultSet.getInt("id"));
        e.setFirst_name(myResultSet.getString("first_name"));
        e.setPa_surname(myResultSet.getString("pa_surname"));
        e.setMa_surname(myResultSet.getString("ma_surname"));
        e.setEmail(myResultSet.getString("email"));
        e.setSalary(myResultSet.getFloat("salary"));
        e.setCurp(myResultSet.getString("curp"));

        return e;
    }
}
