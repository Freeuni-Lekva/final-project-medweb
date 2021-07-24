package freeuni.edu.ge;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AdministratorDao adminDAO = new InMemoryAdmnDao();
        PatientDAO patientDao = new PatientDAO();
        servletContextEvent.getServletContext().setAttribute("PatientBase", patientDao);
        servletContextEvent.getServletContext().setAttribute("AdministratorDAO",adminDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
