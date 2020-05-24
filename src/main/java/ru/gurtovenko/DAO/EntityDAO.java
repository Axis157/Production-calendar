package ru.gurtovenko.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public abstract class EntityDAO<T> {

    @Autowired
    SessionFactory sessionFactory;

    public Session currentSession() {
        return sessionFactory.openSession();
    }

    public void add(T entity) throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void update(T entity) throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void remove(T entity) throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }
}
