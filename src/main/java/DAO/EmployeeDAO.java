package DAO;

import Entity.Employee;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class EmployeeDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Employee getEmployeeByID(int key) {
        return sessionFactory.getCurrentSession().get(Employee.class, key);
    }

    public List<Employee> getAllEmployees() {
        return sessionFactory.getCurrentSession().createQuery("from Employee").getResultList();
    }

    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }
}
