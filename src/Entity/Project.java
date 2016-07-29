package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Projects", catalog = "Entity", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "ProjectName")
})
public class Project {
    private int ID;
    private String name;
    private List<Assigment> assigments;
    private List<Technology> technologies;

    public Project() {
    }

    public Project(int ID, String name, List<Assigment> assigments) {
        this.ID = ID;
        this.name = name;
        this.assigments = assigments;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    @Column(name = "ProjectName", nullable = false)
    public String getName() {
        return name;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assigment> getAssigments() {
        return assigments;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssigments(List<Assigment> assigments) {
        this.assigments = assigments;
    }

    @Override
    public String toString() {
        return "Project{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", assigments=" + assigments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        return assigments != null ? assigments.equals(project.assigments) : project.assigments == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (assigments != null ? assigments.hashCode() : 0);
        return result;
    }
}
