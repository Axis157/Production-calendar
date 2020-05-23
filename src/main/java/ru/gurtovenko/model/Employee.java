package ru.gurtovenko.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "position")
    private String position;

    @Column(name = "tabel")
    private Long tabel;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_department")
    private Department department;

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getTabel() {
        return tabel;
    }

    public void setTabel(Long tabel) {
        this.tabel = tabel;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public StringProperty fioProperty(){
        return new SimpleStringProperty(firstName+" "+lastName);
    }
    public StringProperty positionProperty(){
        return new SimpleStringProperty(position);
    }
    public LongProperty tabelProperty(){
        return new SimpleLongProperty(tabel);
    }
}
