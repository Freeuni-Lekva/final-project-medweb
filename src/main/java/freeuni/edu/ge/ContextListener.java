package freeuni.edu.ge;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("aqaa");
        AdministratorDao adminDAO = new InMemoryAdmnDao();
        servletContextEvent.getServletContext().setAttribute("AdministratorDAO",adminDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
