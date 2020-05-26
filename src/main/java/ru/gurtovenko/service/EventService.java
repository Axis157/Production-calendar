package ru.gurtovenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gurtovenko.DAO.EntityDAO;
import ru.gurtovenko.model.Event;

import java.sql.SQLException;
import java.util.List;

@Component
public class EventService extends EntityDAO<Event> {

    public List<Event> getAll() throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        List<Event> list = session.createQuery("from Event",
                Event.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    public Event getById(Long id) throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Event> query = session.createQuery(
                "from Event where id = :id",Event.class);
        query.setParameter("id", id);
        Event event = query.getSingleResult();
        transaction.commit();
        session.close();
        return event;
    }
}
