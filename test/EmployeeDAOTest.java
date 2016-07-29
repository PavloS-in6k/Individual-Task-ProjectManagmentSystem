import DAO.EmployeeDAO;
import Entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class EmployeeDAOTest {
    @Autowired
    EmployeeDAO employeeDAO;

    @Before
    public void setUp() throws Exception {
        DB.setUpDB();
    }

    @Test
    public void getEmployeeByID() throws Exception {
        Employee employee = new Employee(0, "Ahmed", "jsmasta");

        assertThat(employeeDAO.getEmployeeByID(0), equalTo(employee));
    }

    @Test
    public void getThirdEmployee() throws Exception {
        Employee employee = new Employee(2, "Celly", "zkfan");

        assertThat(employeeDAO.getEmployeeByID(2), equalTo(employee));
    }

    @Test
    public void getAllEmployees() throws Exception {
        List<Employee> employees = asList(
                new Employee(0, "Ahmed", "jsmasta"),
                new Employee(1, "Billy", "jsnoob"),
                new Employee(2, "Celly", "zkfan"),
                new Employee(3, "Dilly", "JavaWeb"),
                new Employee(4, "Ellias", "javaJunior")
        );

        assertThat(employeeDAO.getAllEmployees(), contains(employees.toArray()));
    }

}
