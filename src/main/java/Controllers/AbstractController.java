package Controllers;

import Context.MyServletContextListener;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet{
    public void init(){
        MyServletContextListener.setupBeans(this);
    }
}
