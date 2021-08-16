package freeuni.edu.ge.Listeners;

import freeuni.edu.ge.DAO.Interfaces.GeneralCommands;
import freeuni.edu.ge.DAO.SQLImplementation.GeneralCommandsSQL;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private String PASSWORD = "misho99tedy";
    private String USER = "root";
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/medweb");

        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        GeneralCommands dao = new GeneralCommandsSQL(dataSource);
        httpSessionEvent.getSession().setAttribute("DAO",dao);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}
