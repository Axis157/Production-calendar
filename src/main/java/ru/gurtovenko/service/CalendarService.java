package ru.gurtovenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.gurtovenko.DAO.EntityDAO;
import ru.gurtovenko.model.Calendar;
import ru.gurtovenko.model.Department;

import java.sql.SQLException;
import java.util.List;

@Component
public class CalendarService extends EntityDAO<Calendar> {

    public List<Calendar> getAll() throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        List<Calendar> list = currentSession().createQuery("from Calendar",
                Calendar.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    public Calendar getById(Long id) throws SQLException {
        Query<Calendar> query = currentSession().createQuery(
                "from Calendar where id = :id",Calendar.class);
        query.setParameter("id", id);
        return query.list().stream().findAny().orElse(null);
    }
}
