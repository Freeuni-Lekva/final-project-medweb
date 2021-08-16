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
    void addVisits(Visit visit) throws SQLException;

    Iterator<History> getPatientHistory(String index) throws SQLException;

    void reserveDoctorVisit(Doctor doctor, Date date, Time time) throws SQLException;
    //Iterator<History> getDoctorHistory(String index) throws SQLException;
    Map<String, Map<Date, List<Time>>> getAllDoctorWorkingTime() throws SQLException;
    void addDoctor(Doctor doctor) throws SQLException;
    List<Doctor> getDoctorByDegreeAndSpecialty(Doctor.DoctorSpecialities specialty, Doctor.Doctor_Qualifications degree) throws SQLException;

    void updateDoctorWorkingTimeBase() throws SQLException;

    Date stringToDate(String s);

    Time stringToTime(String s);
}
