package ru.gurtovenko.util;

import ru.gurtovenko.model.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static ru.gurtovenko.MainApp.*;

public class InitData {

    public static void dataInDB() throws SQLException {
        if(getEventService().getAll().isEmpty()){
            for(Mark mark: Mark.values()){
                Event event = new Event();
                event.setEvent(mark.name());
                getEventService().add(event);
            }
        }
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
            Department department = getDepartmentService().getById(1L);
            employee1.setDepartment(department);
            getEmployeeService().add(employee1);

            employee2.setFirstName("Роман");
            employee2.setLastName("Романов");
            employee2.setPosition("IT специалист");
            employee2.setTabel(2L);
            employee2.setDepartment(department);
            getEmployeeService().add(employee2);

            employee3.setFirstName("Сергей");
            employee3.setLastName("Сергеевич");
            employee3.setPosition("Администратор");
            employee3.setTabel(3L);
            department = getDepartmentService().getById(2L);
            employee3.setDepartment(department);
            getEmployeeService().add(employee3);
        }
        if(getCalendarService().getAll().isEmpty()){
            GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, GregorianCalendar.JANUARY, 1);
            System.out.println(gregorianCalendar.get(GregorianCalendar.YEAR));
            for(Employee employee: getEmployeeService().getAll()){
                while (gregorianCalendar.get(GregorianCalendar.YEAR) != 2021){
                    Calendar calendar = new Calendar();
                    CalendarId calendarId = new CalendarId();
                    Date date = new Date(gregorianCalendar.getTimeInMillis());
                    calendarId.setCaldate(date);
                    calendarId.setIdEmployee(employee);
                    calendar.setId(calendarId);
                    calendar.setEvent(getEventService().getById(1L));

                    getCalendarService().add(calendar);
                    gregorianCalendar.add(GregorianCalendar.DATE, 1);
                }
                System.out.println(gregorianCalendar.get(GregorianCalendar.YEAR));
                gregorianCalendar = new GregorianCalendar(2020, GregorianCalendar.JANUARY, 1);
            }

        }
    }
}
