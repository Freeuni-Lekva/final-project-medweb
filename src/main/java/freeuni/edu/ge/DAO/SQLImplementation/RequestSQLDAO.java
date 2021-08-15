package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.Models.Request;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class RequestSQLDAO {
    private BasicDataSource dataSource;


    public RequestSQLDAO(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }


    //Doctor command
    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "INSERT INTO requests (name, surname, ID) Values (\"" + name + "\", \"" + surname + "\", \"" + ID + "\")";
        statement.executeUpdate(insertSQl);

    }

    //Doctor command
    public void addNewDoctorRegistrationRequest(Request request) throws SQLException {
        addNewDoctorRegistrationRequest(request.getName(),request.getSurname(),request.getID());
    }

    //Administrator command
    public Iterator<Request> getIterator() throws SQLException {
        dataSource.restart();
        ArrayList<Request> listOfRequests = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from requests";
        ResultSet results = statement.executeQuery(sql);
        while(results.next()){
            listOfRequests.add(convertToRequest(results));
        }

        Iterator<Request> it = listOfRequests.iterator();
        return it;
    }

    private Request convertToRequest(ResultSet results) throws SQLException {
        String name = results.getString("name");
        String surname = results.getString("surname");
        String ID = results.getString("ID");
        return new Request(name,surname,ID);
    }

    //Administrator command
    public void requestAnswer(boolean answer, String ID) throws SQLException {
        if(answer) {
            removeAndAddToCanRegisterTable(ID);
        } else {
            removeFromRequest(ID);
        }
    }

    private void removeFromRequest(String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "Delete from requests where ID = \""+ ID +"\"";
        statement.executeUpdate(insertSQl);
    }

    private void removeAndAddToCanRegisterTable(String ID) throws SQLException {
        Request request = getRequestByID(ID);
        removeFromRequest(ID);
        addDoctorToCanRegisterTable(request);
    }

    //Doctor command
    public boolean canDoctorRegister(String name, String surname, String ID) throws SQLException {
        return getRequestFromCanRegisterTableByID(ID) != null;
    }

    //Administrator command
    public void addDoctorToCanRegisterTable(Request request) throws SQLException {
        addDoctorToCanRegisterTable(request.getName(),request.getSurname(),request.getID());
    }

    //Doctor command
    public void removeFromCanRegisterTable(String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "Delete from canregister where ID = \""+ ID +"\"";
        statement.executeUpdate(insertSQl);
    }

    //Administrator command
    public void addDoctorToCanRegisterTable(String name, String surname, String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "INSERT INTO canregister (name, surname, ID) Values (\"" + name + "\", \"" + surname + "\", \"" + ID + "\")";
        statement.executeUpdate(insertSQl);
    }

    //method for tests
    public Request getRequestFromCanRegisterTableByID(String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from canregister where ID = \"" + ID + "\" ;";
        ResultSet result = statement.executeQuery(sql);
        Request request = null;
        while(result.next()){
            request = convertToRequest(result);
        }
        return request;
    }

    public String getNameById(String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select name from requests where ID = \"" + ID + "\" ;";
        ResultSet result = statement.executeQuery(sql);
        String res = "";
        while(result.next()){
            res = result.getString("name");
        }
        return res;
    }

    public String getSurnameById(String ID) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select surname from requests where ID = \"" + ID + "\" ;";
        ResultSet result = statement.executeQuery(sql);
        String res = "";
        while(result.next()){
            res = result.getString("surname");
        }
        return res;
    }

    public Request getRequestByID(String ID) throws SQLException {
        String name = getNameById(ID);
        String surname = getSurnameById(ID);
        return new Request(name, surname, ID);
    }
}