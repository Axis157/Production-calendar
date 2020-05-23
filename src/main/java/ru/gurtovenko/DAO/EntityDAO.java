package ru.gurtovenko.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gurtovenko.model.Department;

import java.sql.SQLException;
import java.util.List;

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

//    public List<T> getAll() throws SQLException {
//        Session session = currentSession();
//        Transaction transaction = session.beginTransaction();
//        List<T> list = currentSession().createQuery("from Department",
//                Department.class).list();
//        transaction.commit();
//        session.close();
//        return list;
//    }

//    public T getById(Long id) throws SQLException {
//        Class clazz = new T().getClass();
//        Query<T> query = currentSession().createQuery("from Department where id = :id", new T().getClass());
//        query.setParameter("id", id);
//        return query.list().stream().findAny().orElse(null);
//    }

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
