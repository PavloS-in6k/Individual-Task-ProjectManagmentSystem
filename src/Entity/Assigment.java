package Entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Assigments", catalog = "Entity", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "ProjectID"),
        @UniqueConstraint(columnNames = "EmployeeID")
})
public class Assigment {
    private int ID;
    private Employee employee;
    private Project project;
    private List<Technology> technologies;

    public Assigment() {
    }

    public Assigment(int ID, Employee employee, Project project, List<Technology> technologies) {
        this.ID = ID;
        this.employee = employee;
        this.project = project;
        this.technologies = technologies;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EmployeeID", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProjectID", nullable = false)
    public Project getProject() {
        return project;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}
