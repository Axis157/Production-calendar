package ru.gurtovenko.model.entityfx;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.util.EmployeeJavaFXFactory;

import java.sql.SQLException;
import java.util.List;

public class DepartmentJavaFX {

    SimpleLongProperty id;
    SimpleStringProperty department;
    ObservableList<EmployeeJavaFX> employeeJavaFX;

    public DepartmentJavaFX(Department department) throws SQLException {
        this.id = new SimpleLongProperty(department.getId());
        this.department = new SimpleStringProperty(department.getDepartment());
        this.employeeJavaFX = FXCollections.observableArrayList(EmployeeJavaFXFactory.getEntityJavaFX(department));
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public ObservableList<EmployeeJavaFX> getEmployeeJavaFX(int month) {
        ObservableList<EmployeeJavaFX> employeeJavaFXES = FXCollections.observableArrayList();
        for(EmployeeJavaFX employee: employeeJavaFX){
            if(employee.getMonth() == month) {
                employeeJavaFXES.add(employee);
            }
        }
        return employeeJavaFXES;
    }

    public void setEmployeeJavaFX(ObservableList<EmployeeJavaFX> employeeJavaFX) {
        this.employeeJavaFX = employeeJavaFX;
    }
}
