package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.DAO.Interfaces.DoctorCommands;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.History;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DoctorCommandsSQL implements DoctorCommands {
    private DoctorSqlDAO doctorDAO;
    private VisitsSQLDAO visitsDAO;
    private PatientSqlDAO patientDAO;
    private HistorySqlDAO historyDAO;
    private WorkingTimesSQL workingTimesSQL;
    public DoctorCommandsSQL(BasicDataSource dataSource){
        doctorDAO = new DoctorSqlDAO(dataSource);
        visitsDAO = new VisitsSQLDAO(dataSource);
        patientDAO = new PatientSqlDAO(dataSource);
        historyDAO = new HistorySqlDAO(dataSource);
        workingTimesSQL = new WorkingTimesSQL(dataSource);
    }

    @Override
    public Doctor getDoctorById(String id) throws SQLException {
        return doctorDAO.getDoctorByIdNumber(id);
    }

    @Override
    public Iterator<Visit> getDoctorVisitsIterator(String ID, String type) throws SQLException {
        return visitsDAO.getDoctorVisitsIterator(ID,type);
    }

    @Override
    public void updateInfo(Doctor doctor) throws SQLException {
        if(doctorDAO.getDoctorByIdNumber(doctor.getID()) != null){
            doctorDAO.removeDoctor(doctor);
            doctorDAO.addDoctor(doctor);
        }
    }

    @Override
    public Patient getPatientById(String id) throws SQLException {
        return patientDAO.getPatientByIdNumber(id);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) throws SQLException {
        return doctorDAO.updateDoctorInfo(doctor);
    }

    @Override

    public Iterator<History> getDoctorHistory(String index) throws SQLException {
        return historyDAO.getDoctorHistory(index);
    }

    public void deleteVisitByPatientAndDoctorId(String patientId, String doctorId) throws SQLException {
        visitsDAO.deleteVisitByPatientAndDoctorId(patientId, doctorId);
    }

    @Override
    public Visit getVisitByPatientAndDoctorId(String patientId, String doctorId) throws SQLException {
        return visitsDAO.getVisitByPatientAndDoctorId(patientId, doctorId);
    }

    @Override
    public void finishVisit(Visit visit, String conclusion) throws SQLException {
        historyDAO.addFinishedVisit(visit, conclusion);
    }
}
