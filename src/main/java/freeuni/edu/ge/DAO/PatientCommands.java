package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;

import java.sql.SQLException;
import java.util.Iterator;

public interface PatientCommands {
    Patient getPatientById(String id) throws SQLException;
    boolean hasVisits(String id, String type) throws SQLException;
    Iterator<Visit> getPatientVisitsIterator(String ID, String type) throws SQLException;
    Doctor getDoctorById(String id) throws SQLException;
}
