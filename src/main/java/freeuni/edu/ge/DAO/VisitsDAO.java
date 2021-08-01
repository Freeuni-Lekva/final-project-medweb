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

public class VisitsDAO implements VisitDAO{
    private BasicDataSource dataSource;

    public VisitsDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
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

        return result.iterator();
    }

    @Override
    public int addVisit(Visit visit) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO visits VALUES (?, ?, ?, ?)");
        statement.setString(1, visit.getPatientId());
        statement.setString(2, visit.getDoctorId());
        statement.setString(3, visit.getReason());
        statement.setString(4, visit.getDate());
        return statement.executeUpdate();
    }

    @Override
    public Visit getVisitByPatientAndDoctorId(String patientId, String doctorId) {
        return null;
    }

    @Override
    public void deleteVisitByPatientAndDoctorId(String patientId, String doctorId) {

    }
}
