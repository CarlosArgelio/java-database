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

        System.out.println("Buscar Uno");
        int employeeId = 3;
        Employee employee = em.find(Employee.class, employeeId);
        System.out.println("Empleado encontrado" + employee);

//        System.out.println("Crear uno");
//        Employee newEmployee = new Employee();
//        newEmployee.setFirstName("Carlos");
//        newEmployee.setPaSurname("Palacios");
//        newEmployee.setMaSurname("Ramos");
//        newEmployee.setEmail("carlos@mail.com");
//        newEmployee.setSalary((float)100);
//        newEmployee.setCurp("123456789012345678");
//
//        em.getTransaction().begin();
//        em.persist(newEmployee);
//        em.getTransaction().commit();
//
//        System.out.println("Nuevo empleado creado: " + newEmployee);

//        System.out.println("Actualizar");
//        int employeeToUpdateId = 2;
//        Employee employeeToUpdate = em.find(Employee.class, employeeToUpdateId);
//        System.out.println("Empleado a modificar: " + employeeToUpdate);
//
//        employeeToUpdate.setFirstName("Nuevo nombre");
//
//        em.getTransaction().begin();
//        em.merge(employeeToUpdate);
//        em.getTransaction().commit();
//
//        System.out.println("Empleado actualizado: " + employeeToUpdate);

        System.out.println("Eliminar");
        int employeeToDeleteId = 1;
        Employee employeeToDelete = em.find(Employee.class, employeeToDeleteId);
        System.out.println("Empleado a eliminar: " + employeeToDelete);

        em.getTransaction().begin();
        em.remove(employeeToDelete);
        em.getTransaction().commit();
        em.close();

    }
}