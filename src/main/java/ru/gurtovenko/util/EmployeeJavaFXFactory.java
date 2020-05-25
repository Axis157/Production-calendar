package ru.gurtovenko.util;

import javafx.beans.property.SimpleStringProperty;
import ru.gurtovenko.model.Calendar;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.entityfx.EmployeeJavaFX;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.gurtovenko.MainApp.getCalendarService;

public class EmployeeJavaFXFactory {

    public static List<EmployeeJavaFX> getEntityJavaFX(int month, Department department) throws SQLException {
        List<Calendar> calendarList = getCalendarService().getByMonth(month);
        Map<String, EmployeeJavaFX> entityJavaFXMap = new HashMap<>();
        for(Calendar calendar: calendarList){
            if(!calendar.getId().getIdEmployee().getDepartment().getId().equals(department.getId())) continue;

            String firstName = calendar.getId().getIdEmployee().getFirstName();
            String lastName = calendar.getId().getIdEmployee().getLastName();

            String fullName = firstName + " " + lastName;
            String position = calendar.getId().getIdEmployee().getPosition();
            Long tabel = calendar.getId().getIdEmployee().getTabel();
            String mark = calendar.getEvent().getEvent();
            if(!entityJavaFXMap.containsKey(fullName)){
                EmployeeJavaFX entityJavaFX = new EmployeeJavaFX();
                entityJavaFX.setFullName(fullName);
                entityJavaFX.setPosition(position);
                entityJavaFX.setTabel(tabel);
                entityJavaFX.getDateMark().add(new SimpleStringProperty(mark));
                entityJavaFXMap.put(fullName,entityJavaFX);
            }
            else {
                EmployeeJavaFX entityJavaFX = entityJavaFXMap.get(fullName);
                entityJavaFX.getDateMark().add(new SimpleStringProperty(mark));
            }
        }
        return new ArrayList<>(entityJavaFXMap.values());
    }

    public static List<EmployeeJavaFX> getEntityJavaFX(Department department) throws SQLException {
        List<Calendar> calendarList = getCalendarService().getAll();
        Map<String, EmployeeJavaFX> entityJavaFXMap = new HashMap<>();
        for(Calendar calendar: calendarList){
            if(!calendar.getId().getIdEmployee().getDepartment().getId().equals(department.getId())) continue;

            String firstName = calendar.getId().getIdEmployee().getFirstName();
            String lastName = calendar.getId().getIdEmployee().getLastName();

            String fullName = firstName + " " + lastName;
            String position = calendar.getId().getIdEmployee().getPosition();
            Long tabel = calendar.getId().getIdEmployee().getTabel();
            String mark = calendar.getEvent().getEvent();
            int month = calendar.getId().getCaldate().getMonth();
            if(!entityJavaFXMap.containsKey(fullName)){
                EmployeeJavaFX entityJavaFX = new EmployeeJavaFX();
                entityJavaFX.setFullName(fullName);
                entityJavaFX.setPosition(position);
                entityJavaFX.setTabel(tabel);
                entityJavaFX.getDateMark().put(month, mark);
                entityJavaFX.setMonth(month);
                entityJavaFXMap.put(fullName,entityJavaFX);
            }
            else {
                EmployeeJavaFX entityJavaFX = entityJavaFXMap.get(fullName);
                entityJavaFX.getDateMark().add(new SimpleStringProperty(mark));
            }
        }
        return new ArrayList<>(entityJavaFXMap.values());
    }
}
