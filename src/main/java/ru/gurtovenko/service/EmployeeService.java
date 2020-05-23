package ru.gurtovenko.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.gurtovenko.DAO.EntityDAO;
import ru.gurtovenko.model.Department;
import ru.gurtovenko.model.Employee;

import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeeService extends EntityDAO<Employee> {

    public List<Employee> getAll() throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> list = currentSession().createQuery("from Employee",
                Employee.class).list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Employee> getAllByIdDepartment(Long id) throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Employee> query = currentSession().createQuery(
                "from Employee where id_department = :id_department",Employee.class);
        query.setParameter("id_department", id);
        List<Employee> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public Employee getById(Long id) throws SQLException {
        Session session = currentSession();
        Transaction transaction = session.beginTransaction();
        Query<Employee> query = currentSession().createQuery(
                "from Employee where id = :id",Employee.class);
        query.setParameter("id", id);
        Employee employee = query.list().stream().findAny().orElse(null);
        transaction.commit();
        session.close();
        return employee;
    }
}
