package DAO;

import Entity.Assigment;
import Entity.Project;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProjectDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Project getProjectByID(int key){

        Project project = (Project) sessionFactory.getCurrentSession()
                .createQuery("select project from Project project where project.id = :id")
                .setParameter("id", key)
                .getSingleResult();

        Hibernate.initialize(project.getAssigments());
        Hibernate.initialize(project.getTechnologies());

        return project;
    }
    //fetch all properties

}
