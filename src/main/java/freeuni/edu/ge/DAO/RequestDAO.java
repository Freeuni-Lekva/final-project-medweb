package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Request;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class RequestDAO {
    private DataSource dataSource;


    public RequestDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }


    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        String insertSQl = "INSERT INTO requests (name, surname, ID) Values (\"" + name + "\", \"" + surname + "\", \"" + ID + "\")";
        statement.executeUpdate(insertSQl);
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

    public String getNameById(String ID){
        return "";
    }

    public String getSurnameById(String ID){
        return "";
    }
}
