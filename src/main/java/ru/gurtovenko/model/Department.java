package ru.gurtovenko.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.gurtovenko.util.EntityJavaFX;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department")
    private String department;

    @Transient
    private Map<Integer, Map<Integer, EntityJavaFX>> entityJavaFX;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public StringProperty departmentProperty(){
        return new SimpleStringProperty(department);
    }

    public Map<Integer, Map<Integer, EntityJavaFX>> getEntityJavaFX() {
        return entityJavaFX;
    }

    public void setEntityJavaFX(Map<Integer, Map<Integer, EntityJavaFX>> entityJavaFX) {
        this.entityJavaFX = entityJavaFX;
    }
}
