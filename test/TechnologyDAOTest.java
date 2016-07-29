import DAO.TechnologyDAO;
import Entity.Technology;
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
public class TechnologyDAOTest {

    @Autowired
    TechnologyDAO technologyDAO;

    @Before
    public void setUp() throws Exception {
        DB.setUpDB();
    }

    @Test
    public void getTechnologyByID() throws Exception {
        //<Technologies ID="0" TechnologyName="AngularJS"/>
        Technology technology = new Technology(0,"AngularJS");

        assertThat(technologyDAO.getTechnologyByID(0), technology);
    }
}
