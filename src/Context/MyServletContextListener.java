package Context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    private static ClassPathXmlApplicationContext applicationContext;

    public static void setupBeans(Object o) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(o);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring-config.xml"});
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("an fail");
    }
}
