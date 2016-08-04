package Controllers;

import Context.MyServletContextListener;
import DAO.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class EmployeeShowController{
    @Autowired
    EmployeeDAO employeeDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("employees", employeeDAO.getAllEmployees());
        return "employees";
    }

//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        employeeDAO = MyServletContextListener.getBean(EmployeeDAO.class);
//
//        request.setAttribute("employees", employeeDAO.getAllEmployees());
//
//        request.getRequestDispatcher("Pages/employees.jsp").forward(request, response);
//    }

}
