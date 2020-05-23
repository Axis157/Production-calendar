package ru.gurtovenko.util;

import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.Employee;

import java.sql.SQLException;

import static ru.gurtovenko.MainApp.*;

public class InitData {

    public static void dataInDB() throws SQLException {
        if(getDepartmentService().getAll().isEmpty()){
            Department department1 = new Department();
            department1.setDepartment("Department");
            getDepartmentService().add(department1);
            department1.setDepartment(department1.getDepartment()+department1.getId());
            getDepartmentService().update(department1);

            Department department2 = new Department();
            department2.setDepartment("Department");
            getDepartmentService().add(department2);
            department2.setDepartment(department2.getDepartment()+department2.getId());
            getDepartmentService().update(department2);
        }
        if(getEmployeeService().getAll().isEmpty()){
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            Employee employee3 = new Employee();

            employee1.setFirstName("Иван");
            employee1.setLastName("Иванов");
            employee1.setPosition("IT специалист");
            employee1.setTabel(1L);
            employee1.setDepartment(getDepartmentService().getById(1L));

            employee2.setFirstName("Роман");
            employee2.setLastName("Романов");
            employee2.setPosition("IT специалист");
            employee2.setTabel(2L);
            employee1.setDepartment(getDepartmentService().getById(1L));

            employee3.setFirstName("Сергей");
            employee3.setLastName("Сергеевич");
            employee3.setPosition("Администратор");
            employee3.setTabel(3L);
            employee1.setDepartment(getDepartmentService().getById(2L));

            getEmployeeService().add(employee1);
            getEmployeeService().add(employee2);
            getEmployeeService().add(employee3);
        }
    }
}
