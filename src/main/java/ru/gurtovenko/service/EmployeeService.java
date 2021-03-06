package ru.gurtovenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.gurtovenko.DAO.EntityDAO;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.Employee;

import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeService extends EntityDAO<Employee> {

    public List<Employee> getAll() throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> list = session.createQuery("from Employee",
                Employee.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Employee> getAllByDepartment(Department id) throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Employee> query = session.createQuery(
                "from Employee as e where e.department = :department",Employee.class);
        query.setParameter("department", id);
        List<Employee> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public Employee getById(Long id) throws SQLException {
//        Session session = getSessionFactory().getCurrentSession();
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Employee> query = session.createQuery(
                "from Employee where id = :id",Employee.class);
        query.setParameter("id", id);
        Employee employee = query.list().stream().findAny().orElse(null);
        transaction.commit();
        session.close();
        return employee;
    }
}
