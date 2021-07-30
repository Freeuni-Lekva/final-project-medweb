package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Request;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class RequestDAO {
    private BasicDataSource dataSource;


    public RequestDAO(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }


    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "INSERT INTO requests (name, surname, ID) Values (\"" + name + "\", \"" + surname + "\", \"" + ID + "\")";
        statement.executeUpdate(insertSQl);
    }

    public void addNewDoctorRegistrationRequest(Request request) throws SQLException {
        addNewDoctorRegistrationRequest(request.getName(),request.getSurname(),request.getID());
    }

    public Iterator<Request> getIterator() throws SQLException {
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

    public void requestAnswer(boolean answer, String ID) throws SQLException {
        if(answer) {
            removeAndAddToCanRegisterTable(ID);
        } else {
            removeFromRequest(ID);
        }
    }

    private void removeFromRequest(String ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "Delete from requests where ID = \""+ ID +"\"";
        statement.executeUpdate(insertSQl);
    }

    private void removeAndAddToCanRegisterTable(String ID){

    }

    public String getNameById(String ID) throws SQLException {
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
