package Controllers;

import Context.MyServletContextListener;
import DAO.EmployeeDAO;
import Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class EmployeeShowController extends AbstractController {
    @Autowired
    EmployeeDAO employeeDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        employeeDAO = MyServletContextListener.getBean(EmployeeDAO.class);

        request.setAttribute("employees", employeeDAO.getAllEmployees());

        request.getRequestDispatcher("Pages/employees.jsp").forward(request, response);
    }

}
