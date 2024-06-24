package org.example.main;

import org.example.Util.UtilEntity;
import org.example.entity.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = UtilEntity.getEntityManager();
        List<Employee> employees = em.createQuery("SELECT e from Employee e", Employee.class).getResultList();

        System.out.println("Listar todos");

        employees.forEach(System.out::println);
    }
}