import DAO.EmployeeDAO;
import Entity.Employee;
import Entity.Technology;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
public class EmployeeDAOTest {
    @Autowired
    EmployeeDAO employeeDAO;

//    @Before
//    public void setUp() throws Exception {
//        DB.setUpDB();
//    }

    @Test
    public void getEmployeeByID0() throws Exception {
        Employee employee = new Employee(0, "Ahmed", "jsmasta",
                asList(new Technology(0, "AngularJS"), new Technology(1, "NodeJS")));


        assertThat(employeeDAO.getEmployeeByID(0), equalTo(employee));
    }

    @Test
    public void getEmployeeByID2() throws Exception {
        Employee employee = new Employee(2, "Celly", "zkfan", singletonList(new Technology(5, "Vaadin")));

        assertThat(employeeDAO.getEmployeeByID(2), equalTo(employee));
    }

    @Test
    public void getAllEmployees() throws Exception {
        List<Employee> employees = asList(
                new Employee(0, "Ahmed", "jsmasta", asList(new Technology(0, "AngularJS"), new Technology(1, "NodeJS"))),
                new Employee(1, "Billy", "jsnoob", asList(new Technology(0, "AngularJS"))),
                new Employee(2, "Celly", "zkfan", asList(new Technology(5, "Vaadin"))),
                new Employee(3, "Dilly", "JavaWeb", asList(new Technology(4, "Grails"), new Technology(5, "Vaadin"))),
                new Employee(4, "Ellias", "javaJunior", asList(new Technology(2, "Hibernate"), new Technology(3, "Spring")))
        );

        assertThat(employeeDAO.getAllEmployees(), contains(employees.toArray()));
    }

}
