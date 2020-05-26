package ru.gurtovenko.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionManager;
import java.sql.SQLException;

@Component
public class EntityDAO<T> {

    @Autowired
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public Session currentSession() {
        return sessionFactory.openSession();
    }

    public void add(T entity) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void update(T entity) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void remove(T entity) throws SQLException {
//        Session session = sessionFactory.getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }
}
