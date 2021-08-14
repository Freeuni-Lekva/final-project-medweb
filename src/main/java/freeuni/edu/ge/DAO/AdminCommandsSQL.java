package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Request;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Iterator;

public class AdminCommandsSQL implements AdministratorCommands{
    private RequestDAO requestDAO;


    public AdminCommandsSQL(BasicDataSource dataSource){
        requestDAO = new RequestDAO(dataSource);
    }


    @Override
    public Iterator<Request> getIterator() throws SQLException {
        return requestDAO.getIterator();
    }

    @Override
    public void requestAnswer(boolean answer, String ID) throws SQLException {
        requestDAO.requestAnswer(answer,ID);
    }
}
