import DAO.ProjectDAO;
import Entity.Assigment;
import Entity.Employee;
import Entity.Project;
import Entity.Technology;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-test-config.xml"})
//@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class ProjectDAOTest {
    @Autowired
    ProjectDAO projectDAO;

//    @Before
//    public void setUp() throws Exception {
//        DB.setUpDB();
//    }

    @Test
    public void getProjectByID0() throws Exception {
        Employee employee = new Employee(4, "Ellias", "javaJunior", asList(new Technology(2, "Hibernate"),
                new Technology(3, "Spring")));
        Assigment assigment = new Assigment(0, employee, 0, employee.getTechnologies());
        List<Technology> projectTechnologies = asList(new Technology(2, "Hibernate"),
                new Technology(3, "Spring"));
        Project project = new Project(0, "InterLink Project Managment System", asList(assigment), projectTechnologies);
        Project gettedProject = projectDAO.getProjectByID(0);

        assertThat(gettedProject.getAssigments().size(),equalTo(project.getAssigments().size()));
        assertThat(gettedProject.getName(), equalTo(project.getName()));
        assertThat(gettedProject.getTechnologies(), contains(project.getTechnologies().toArray()));
    }
}
