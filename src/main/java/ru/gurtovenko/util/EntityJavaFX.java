package ru.gurtovenko.util;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

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
        String result = "";
        for(SimpleStringProperty s: dateMark){
            result += s.get();
        }
        this.total = new SimpleStringProperty(result);
        return this.total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
