import DAO.AssigmentDAO;
import Entity.Assigment;
import Entity.Employee;
import Entity.Technology;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-config.xml"})
public class AssigmentDAOTest {
    @Autowired
    AssigmentDAO assigmentDAO;

    @Before
    public void setUp() throws Exception {
        DB.setUpDB();
    }

    @Test
    public void getAssigmentByID0() throws Exception {
        /*
        <Projects ID="0" ProjectName="InterLink Project Managment System"/>
        <ProjectTechnologies ProjectID="0" TechnologyID="2"/>
        <ProjectTechnologies ProjectID="0" TechnologyID="3"/>
        <Employees ID="4" Name="Ellias" Surname="javaJunior"/>
        */
        Employee employee = new Employee(4, "Ellias", "javaJunior", asList(new Technology(2, "Hibernate"),
                new Technology(3, "Spring")));
        Assigment assigment = new Assigment(0, employee, 0, employee.getTechnologies());
        Assigment gettedAssigment = assigmentDAO.getAssigmentByID(0);
        assertThat(gettedAssigment, equalTo(assigment));
    }
}