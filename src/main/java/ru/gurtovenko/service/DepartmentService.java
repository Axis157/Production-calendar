package ru.gurtovenko.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gurtovenko.DAO.EntityDAO;
import ru.gurtovenko.model.Department;

import java.sql.SQLException;
import java.util.List;

@Component
public class DepartmentService extends EntityDAO<Department> {

    public List<Department> getAll() throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        List<Department> list = currentSession().createQuery("from Department",
                Department.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    public Department getById(Long id) throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Department> query = currentSession().createQuery(
                "from Department where id = :id",Department.class);
        query.setParameter("id", id);
        Department department = query.list().stream().findAny().orElse(null);
        transaction.commit();
        session.close();
        return department;
    }

}
