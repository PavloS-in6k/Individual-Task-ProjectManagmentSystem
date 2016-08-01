package DAO;

import Entity.Assigment;
import org.hibernate.CacheMode;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AssigmentDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Assigment getAssigmentByID(int key) {
        return sessionFactory.getCurrentSession().get(Assigment.class, key);
    }

    public Assigment getAssigmentEagerByID(int key){
        return (Assigment) sessionFactory.getCurrentSession()
                .createQuery("select assigment from Assigment assigment join fetch assigment.technologies where assigment.ID = :id")
                .setParameter("id",key).getSingleResult();
    }
}
