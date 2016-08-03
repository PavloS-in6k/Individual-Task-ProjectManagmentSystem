package Entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Employees", catalog = "Entity", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "Name"),
        @UniqueConstraint(columnNames = "Surname")
})
public class Employee {
    private int ID;
    private String name;
    private String surname;
    private List<Technology> technologies;

    public Employee() {
    }

    public Employee(int ID, String name, String surname, List<Technology> technologies) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.technologies = technologies;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EmployeeTechnologies",
            joinColumns = {@JoinColumn(name = "EmployeeID")},
            inverseJoinColumns = {@JoinColumn(name = "TechnologyID")}
    )
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", technologies=" + technologies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        return technologies != null ? isEquals(employee) : employee.technologies == null;
    }

    private boolean isEquals(Employee employee) {
        return (technologies.containsAll(employee.technologies) && (technologies.size() == employee.technologies.size()));
    }


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
