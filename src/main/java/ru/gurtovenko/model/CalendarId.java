package ru.gurtovenko.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
public class CalendarId implements Serializable {
    @Column(name = "caldate")
    private Date caldate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee")
    private Employee idEmployee;

    public CalendarId() {
    }

    public Date getCaldate() {
        return caldate;
    }

    public void setCaldate(Date caldate) {
        this.caldate = caldate;
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }
}
