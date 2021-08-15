package freeuni.edu.ge.DAO.Interfaces;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.History;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface DoctorCommands {
    Doctor getDoctorById(String id) throws SQLException;

    Iterator<Visit> getDoctorVisitsIterator(String ID, String type) throws SQLException;

    void updateInfo(Doctor doctor) throws SQLException;

    Patient getPatientById(String id) throws SQLException;

    boolean updateDoctor(Doctor doctor) throws SQLException;

    Iterator<History> getDoctorHistory(String index) throws SQLException;

    void finishVisit(Visit visit, String conclusion) throws SQLException;
    Visit getVisitByPatientAndDoctorId(String patientId, String doctorId) throws SQLException;
    void deleteVisitByPatientAndDoctorId(String patientId, String doctorId) throws SQLException;

}
