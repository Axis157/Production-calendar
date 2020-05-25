package ru.gurtovenko.util;

import javafx.beans.property.SimpleStringProperty;
import ru.gurtovenko.model.Calendar;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.gurtovenko.MainApp.*;

public class EntityJavaFXFactory {

    public static List<EntityJavaFX> getEntityJavaFX(int month) throws SQLException {
        List<Calendar> calendarList = getCalendarService().getByMonth(month);
        Map<String, EntityJavaFX> entityJavaFXMap = new HashMap<>();
        for(Calendar calendar: calendarList){
            String firstName = calendar.getId().getIdEmployee().getFirstName();
            String lastName = calendar.getId().getIdEmployee().getLastName();

            String fullName = firstName + " " + lastName;
            String position = calendar.getId().getIdEmployee().getPosition();
            Long tabel = calendar.getId().getIdEmployee().getTabel();
            String mark = calendar.getEvent().getEvent();
            if(!entityJavaFXMap.containsKey(fullName)){
                EntityJavaFX entityJavaFX = new EntityJavaFX();
                entityJavaFX.setFullName(fullName);
                entityJavaFX.setPosition(position);
                entityJavaFX.setTabel(tabel);
                entityJavaFX.getDateMark().add(new SimpleStringProperty(mark));
                entityJavaFXMap.put(fullName,entityJavaFX);
            }
            else {
                EntityJavaFX entityJavaFX = entityJavaFXMap.get(fullName);
                entityJavaFX.getDateMark().add(new SimpleStringProperty(mark));
            }
        }
        return new ArrayList<>(entityJavaFXMap.values());
    }
}
