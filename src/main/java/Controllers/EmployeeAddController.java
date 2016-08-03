package Controllers;

import Context.MyServletContextListener;
import DAO.EmployeeDAO;
import DAO.TechnologyDAO;
import Entity.Employee;
import Entity.Technology;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeAddController extends AbstractController {
    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    TechnologyDAO technologyDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        technologyDAO = MyServletContextListener.getBean(TechnologyDAO.class);

        request.setAttribute("technologies", technologyDAO.getAllTechnologies());

        request.getRequestDispatcher("Pages/addEmployee.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeDAO = MyServletContextListener.getBean(EmployeeDAO.class);
        technologyDAO = MyServletContextListener.getBean(TechnologyDAO.class);

        try {
            System.out.println(request.getParameter("employeeName"));
            System.out.println(request.getParameter("employeeSurname"));
            System.out.println(Arrays.toString(request.getParameterValues("technologiesSelect")));
            List<Technology> technologies = new ArrayList<>();
            for (String technologyName : request.getParameterValues("technologiesSelect")) {
                technologies.add(technologyDAO.getTechnologyByName(technologyName));
            }
            Employee employee = new Employee(
                    request.getParameter("employeeName"),
                    request.getParameter("employeeSurname"),
                    technologies);
            employeeDAO.addEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
