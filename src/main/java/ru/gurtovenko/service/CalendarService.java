package ru.gurtovenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gurtovenko.DAO.EntityDAO;
import ru.gurtovenko.model.Calendar;

import java.sql.SQLException;
import java.util.List;

@Component
public class CalendarService extends EntityDAO<Calendar> {

    public List<Calendar> getAll() throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        List<Calendar> list = session.createQuery("from Calendar",
                Calendar.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Calendar> getByMonth(Integer id) throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Calendar> query = session.createNativeQuery(
                "SELECT * FROM \"calendar\" WHERE MONTH(\"caldate\") = :id").addEntity(Calendar.class);
//        Query<Calendar> query = currentSession().createQuery(
//                "from Calendar where id = :id",Calendar.class);
        query.setParameter("id", id);
        List<Calendar> calendars = query.list();
        transaction.commit();
        session.close();
        return calendars;
    }
}
