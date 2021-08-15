package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.Models.History;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HistorySqlDAO {

    private final BasicDataSource dataSource;

    public HistorySqlDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterator<History> getPatientHistory(String id) throws SQLException {
        dataSource.restart();

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from history  where Patient_ID_NUMBER = ?;");
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        return returnIterator(resultSet);


    }

    public Iterator<History> getDoctorHistory(String id) throws SQLException {
        dataSource.restart();

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from history  where Doctor_ID_NUMBER = ?;");
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();
        return returnIterator(resultSet);

    }

    private ListIterator<History> returnIterator(ResultSet resultSet) throws SQLException {
        List<History>  history = new ArrayList<>();

        while(resultSet.next()) {
            history.add(new History(new Visit(resultSet.getString("Patient_ID_NUMBER"), resultSet.getString("Doctor_ID_NUMBER"),
                    resultSet.getString("Reason"), resultSet.getString("date")),
                    resultSet.getString("Medical_Conclusion")));
        }
        if(history.isEmpty()) return null;
        return history.listIterator();
    }

    public void addFinishedVisit(Visit visit, String conclusion) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into history  (Patient_ID_NUMBER , " +
                    " Doctor_ID_NUMBER, date,  Reason, Type, Medical_Conclusion)  " +
                    "values (?,?,?,?,?,?,?,?,?,?);");

            statement.setString(1, visit.getPatientId());
            statement.setString(2, visit.getDoctorId());
            statement.setString(3, visit.getDate());
            statement.setString(4, visit.getReason());
            statement.setString(5, visit.getType());
            statement.setString(6, conclusion);


            //System.out.println("Added "+patient.getName());
            //ResultSet resultset = statement.executeQuery();
            statement.executeUpdate();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
