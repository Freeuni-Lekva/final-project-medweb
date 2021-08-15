package freeuni.edu.ge.DAO.Interfaces;

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
    void addPatient(Patient patient) throws SQLException;
    boolean contains(Patient patient) throws SQLException;
    boolean contains(String ID) throws SQLException;
    boolean updatePatientInfo(Patient patient) throws SQLException;
    String getPatientIndex(String id) throws SQLException;
    String getPatientIdByIndex(String index) throws SQLException;
}
