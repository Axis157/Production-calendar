package ru.gurtovenko.model;

import javax.persistence.*;

@Entity
@Table(name = "calendar")
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
