package freeuni.edu.ge.Listeners;

import freeuni.edu.ge.DAO.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AdministratorDao adminDAO = new InMemoryAdmnDao();
        PatientDAOInterface patientDao = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorsDAO();

        servletContextEvent.getServletContext().setAttribute("PatientsBase", patientDao);
        servletContextEvent.getServletContext().setAttribute("AdministratorDAO",adminDAO);
        servletContextEvent.getServletContext().setAttribute("DoctorsDAO", doctorDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
