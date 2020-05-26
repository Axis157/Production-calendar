package ru.gurtovenko.util;

import org.w3c.dom.ls.LSOutput;
import ru.gurtovenko.model.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static ru.gurtovenko.MainApp.*;

public class InitData {

    public static void dataInDB() throws SQLException, IOException {
        if(getEventService().getAll().isEmpty()){
//            java.util.Date date1 = new java.util.Date();
            for(Mark mark: Mark.values()){
                Event event = new Event();
                event.setEvent(mark.name());
                getEventService().add(event);
            }
//            java.util.Date date2 = new java.util.Date();
//            System.out.println("Event: " + (date2.getTime() - date1.getTime()));
        }
        if(getDepartmentService().getAll().isEmpty()){
//            java.util.Date date1 = new java.util.Date();
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
//            java.util.Date date2 = new java.util.Date();
//            System.out.println("Department: " + (date2.getTime() - date1.getTime()));
        }
        if(getEmployeeService().getAll().isEmpty()){
//            java.util.Date date1 = new java.util.Date();
            Employee employee1 = new Employee();
            Employee employee2 = new Employee();
            Employee employee3 = new Employee();
            Employee employee4 = new Employee();

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

            employee4.setFirstName("Андрей");
            employee4.setLastName("Андреевич");
            employee4.setPosition("Администратор");
            employee4.setTabel(4L);
            department = getDepartmentService().getById(2L);
            employee4.setDepartment(department);
            getEmployeeService().add(employee4);

//            java.util.Date date2 = new java.util.Date();
//            System.out.println("Employee: " + (date2.getTime() - date1.getTime()));
        }
        if(getCalendarService().getAll().isEmpty()){
//            java.util.Date date1 = new java.util.Date();
//            GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, GregorianCalendar.JANUARY, 1);
//            for(Employee employee: getEmployeeService().getAll()){
//                String[] dayOfYear = workDayOrNot();
//                int index = 0;
//                while (gregorianCalendar.get(GregorianCalendar.YEAR) != 2021){
//                    Calendar calendar = new Calendar();
//                    CalendarId calendarId = new CalendarId();
//                    Date date = new Date(gregorianCalendar.getTimeInMillis());
//                    calendarId.setCaldate(date);
//                    calendarId.setIdEmployee(employee);
//                    calendar.setId(calendarId);
//                    if(dayOfYear[index].equalsIgnoreCase("0")){
//                        calendar.setEvent(getEventService().getById(1L));
//                    }
//                    else{
//                        calendar.setEvent(getEventService().getById(3L));
//                    }
//
//                    getCalendarService().add(calendar);
//                    index++;
//                    gregorianCalendar.add(GregorianCalendar.DATE, 1);
//                }
//                System.out.println(employee.getFirstName());
//                gregorianCalendar = new GregorianCalendar(2020, GregorianCalendar.JANUARY, 1);
//            }
//            java.util.Date date2 = new java.util.Date();
//            System.out.println("Calendar: " + (date2.getTime() - date1.getTime()));
            for(int i = 1; i < 5; i++){
//                java.util.Date date1 = new java.util.Date();
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO \"calendar\" VALUES(?, ?, ?)");
                GregorianCalendar gregorianCalendar = new GregorianCalendar(
                        2020, GregorianCalendar.JANUARY, 1);
                String[] dayOfYear = workDayOrNot();
                int index = 0;
                while (gregorianCalendar.get(GregorianCalendar.YEAR) != 2021){
                    Date date = new Date(gregorianCalendar.getTimeInMillis());
                    statement.setDate(1, date);
                    statement.setLong(2, i);
                    if(dayOfYear[index].equalsIgnoreCase("0")){
                        statement.setLong(3, 1L);
                    }
                    else{
                        statement.setLong(3, 3L);
                    }
                    statement.executeUpdate();

                    index++;
                    gregorianCalendar.add(GregorianCalendar.DATE, 1);
                }
                statement.close();
                connection.close();
//                java.util.Date date2 = new java.util.Date();
//                System.out.println("Calendar JDBC: " + (date2.getTime() - date1.getTime()));
            }
        }
    }
    public static String[] workDayOrNot() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("workornot.txt")));
        String s = "";
        while(reader.ready()){
            s += reader.readLine();
        }
        reader.close();
        String[] result = s.split("");
        return result;
    }
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver"); //Проверяет наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection("jdbc:h2:./db/data", "sa", ""); //соед. с БД
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
