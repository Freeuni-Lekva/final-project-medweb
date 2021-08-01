package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Visit;

import java.sql.SQLException;
import java.util.Iterator;

public interface VisitDAO {

    Iterator<Visit> getVisitsByPatient(String patientId) throws SQLException;

    int addVisit(Visit visit) throws SQLException;

    Visit getVisitByPatientAndDoctorId(String patientId, String doctorId);

    void deleteVisitByPatientAndDoctorId(String patientId, String doctorId);
}
