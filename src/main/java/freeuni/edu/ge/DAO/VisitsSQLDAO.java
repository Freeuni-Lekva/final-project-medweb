package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VisitsSQLDAO {
    private BasicDataSource dataSource;

    public VisitsSQLDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Iterator<Visit> getVisitsByPatient(String patientId) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("Select * From visits where patientId = ?");
        statement.setString(1, patientId);
        ResultSet resultSet = statement.executeQuery();

        return returnIterator(resultSet);
    }

    private Iterator<Visit> returnIterator(ResultSet resultSet) throws SQLException {
        List<Visit> result = new ArrayList<>();
        while(resultSet.next()) {
            result.add(new Visit(resultSet.getString("patientId"), resultSet.getString("doctorId"),
                    resultSet.getString("reason"), resultSet.getString("date")));
        }
        if(result.isEmpty()) return null;
        return result.iterator();
    }

    public int addVisit(Visit visit) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO visits VALUES (?, ?, ?, ?)");
        statement.setString(1, visit.getPatientId());
        statement.setString(2, visit.getDoctorId());
        statement.setString(3, visit.getReason());
        statement.setString(4, visit.getDate());
        return statement.executeUpdate();
    }

    public Visit getVisitByPatientAndDoctorId(String patientId, String doctorId) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM visits WHERE patientId = ? AND doctorId = ?");
        statement.setString(1, patientId);
        statement.setString(2, doctorId);
        ResultSet resultSet = statement.executeQuery();
        Iterator<Visit> result = returnIterator(resultSet);
        if(result == null) return null;
        return result.next();
    }

    public void deleteVisitByPatientAndDoctorId(String patientId, String doctorId) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM visits WHERE patientId = ? AND doctorId = ?");
        statement.setString(1, patientId);
        statement.setString(2, doctorId);
        statement.executeUpdate();
    }

    //not full implementation, needs visits type change.
    public Iterator<Visit> getDoctorVisitsIterator(String ID, String type) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM visits WHERE doctorId = ?");

        statement.setString(1,ID);
        ResultSet resultSet = statement.executeQuery();

        return returnIterator(resultSet);
    }
}
