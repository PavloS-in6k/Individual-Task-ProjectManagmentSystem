package DAO;

import Entity.Technology;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TechnologyDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Technology getTechnologyByID(int key) {
        return sessionFactory.getCurrentSession().get(Technology.class, key);
    }

    public Technology getTechnologyByName(String name) {
        return (Technology) sessionFactory.getCurrentSession()
                .createQuery("from Technology technology where technology.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    public List<Technology> getAllTechnologies() {
        return sessionFactory.getCurrentSession().createQuery("from Technology").getResultList();
    }
}
