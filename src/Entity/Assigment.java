package Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private int projectID;
    private List<Technology> technologies;

    public Assigment() {
    }

    public Assigment(int ID, Employee employee, int projectID, List<Technology> technologies) {
        this.ID = ID;
        this.employee = employee;
        this.projectID = projectID;
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

    @Column(name = "projectID", nullable = false)
    public int getProject() {
        return projectID;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "AssigmentTechnologies",
            joinColumns = {@JoinColumn(name = "AssigmentID")},
            inverseJoinColumns = {@JoinColumn(name = "TechnologyID")}
    )
    @Fetch(FetchMode.SELECT)
    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setProject(int projectID) {
        this.projectID = projectID;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    @Override
    public String toString() {
        return "Assigment{" +
                "ID=" + ID +
                ", employee=" + employee +
                ", technologies=" + technologies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assigment assigment = (Assigment) o;

        if (projectID != assigment.projectID) return false;
        if (employee != null ? !employee.equals(assigment.employee) : assigment.employee != null) return false;
        return technologies != null ? isEquals(assigment) : assigment.technologies == null;
    }

    private boolean isEquals(Assigment assigment) {
        return ((technologies.containsAll(assigment.technologies)) && (technologies.size() == assigment.technologies.size()));
    }


    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (technologies != null ? technologies.hashCode() : 0);
        return result;
    }
}
