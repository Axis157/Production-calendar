package ru.gurtovenko.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Embeddable
class CalendarId implements Serializable {
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

@Entity
@Table(name = "сalendar")
public class Calendar {

    @EmbeddedId
    private CalendarId id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_event")
    private Event event;

    public Calendar() {
    }

    public CalendarId getId() {
        return id;
    }

    public void setId(CalendarId id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
