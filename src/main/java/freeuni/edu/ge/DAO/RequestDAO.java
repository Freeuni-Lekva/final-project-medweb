package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Request;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public Iterator<Request> getIterator(){
        return null;
    }

    public void requestAnswer(boolean answer, String ID){

    }

    private void removeFromRequest(String ID){

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
