package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PatientCommandsSQL implements PatientCommands {
    private BasicDataSource dataSource;
    private VisitsSQLDAO visitsDAO;
    private PatientSqlDAO patientDAO;
    private DoctorSqlDAO doctorDAO;
    private WorkingTimesSQL workingTimesSQL;


    public PatientCommandsSQL(BasicDataSource dataSource){
        this.dataSource = dataSource;
        visitsDAO = new VisitsSQLDAO(dataSource);
        patientDAO = new PatientSqlDAO(dataSource);
        doctorDAO = new DoctorSqlDAO(dataSource);
        workingTimesSQL = new WorkingTimesSQL(dataSource);
    }

    @Override
    public Patient getPatientById(String id) throws SQLException {
        return patientDAO.getPatientByIdNumber(id);
    }

    @Override
    public boolean hasVisits(String id, String type) throws SQLException {
        return visitsDAO.hasVisits(id,type);
    }

    @Override
    public Iterator<Visit> getPatientVisitsIterator(String ID, String type) throws SQLException {
        return visitsDAO.getPatientVisitsIterator(ID,type);
    }

    @Override
    public Doctor getDoctorById(String id) throws SQLException {
        return doctorDAO.getDoctorByIdNumber(id);
    }

    @Override
    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    @Override
    public boolean contains(Patient patient) throws SQLException {
        return contains(patient.getID());
    }

    @Override
    public boolean contains(String ID) throws SQLException {
        return patientDAO.getPatientByIdNumber(ID) != null;
    }

    @Override
    public boolean updatePatientInfo(Patient patient) throws SQLException {
        return patientDAO.updatePatientInfo(patient);
    }

    @Override
    public String getPatientIndex(String id) throws SQLException {
        return patientDAO.getPatientIndex(id);
    }

    @Override
    public String getPatientIdByIndex(String index) throws SQLException {
        return patientDAO.getPatientIdByIndex(index);
    }

    @Override
    public Map<String, Map<Date, List<Time>>> getAllDoctorWorkingTime() throws SQLException {
        return workingTimesSQL.getAllDoctorWorkingTime();
    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException {
        workingTimesSQL.addDoctor(doctor);
    }

    @Override
    public Iterator<Doctor> getDoctorByDegreeAndSpecialty(Doctor.DoctorSpecialities specialty, Doctor.Doctor_Qualifications degree) throws SQLException {
        Iterator<Doctor> it = doctorDAO.getDoctorByDegreeAndSpecialty(specialty, degree);
        return it;
    }
}
