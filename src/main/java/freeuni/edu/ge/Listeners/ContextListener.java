package freeuni.edu.ge.Listeners;

import freeuni.edu.ge.DAO.InMemoryAdmnDao;
import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.DAO.PatientDAO;
import freeuni.edu.ge.DAO.PatientDAOInterface;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AdministratorDao adminDAO = new InMemoryAdmnDao();
        PatientDAOInterface patientDao = new PatientDAO();
        servletContextEvent.getServletContext().setAttribute("PatientsBase", patientDao);
        servletContextEvent.getServletContext().setAttribute("AdministratorDAO",adminDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
