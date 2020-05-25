package ru.gurtovenko.util;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.*;

public class EntityJavaFX {

    SimpleStringProperty fullName = new SimpleStringProperty();
    SimpleStringProperty position = new SimpleStringProperty();
    SimpleLongProperty tabel = new SimpleLongProperty();
    List<SimpleStringProperty> dateMark = new ArrayList<>();
    SimpleStringProperty total = new SimpleStringProperty();

    public EntityJavaFX() {
    }

    public String getFullName() {
        return fullName.get();
    }

    public SimpleStringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position.set(position);
    }

    public long getTabel() {
        return tabel.get();
    }

    public SimpleLongProperty tabelProperty() {
        return tabel;
    }

    public void setTabel(long tabel) {
        this.tabel.set(tabel);
    }

    public List<SimpleStringProperty> getDateMark() {
        return dateMark;
    }
    public SimpleStringProperty propertyMark(int i){
        return dateMark.get(i);
    }

    public void setDateMark(List<SimpleStringProperty> dateMark) {
        this.dateMark = dateMark;
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        Map<String, Integer> resultMap = new HashMap<>();
        for(SimpleStringProperty s: dateMark){
            if(!resultMap.containsKey(s.get())){
                resultMap.put(s.get(),1);
            }
            else{
                Integer newValue = resultMap.get(s.get()) + 1;
                resultMap.put(s.get(),newValue);
            }
        }
        String str = "";
        for(Map.Entry<String, Integer> pair: resultMap.entrySet()){
            str += ";"+pair.getKey()+"("+pair.getValue()+")";
        }
        this.total = new SimpleStringProperty(str.substring(1));

        return this.total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
