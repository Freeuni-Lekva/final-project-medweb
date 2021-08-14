package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Iterator;

public class PatientCommandsSQL implements PatientCommands{
    private BasicDataSource dataSource;
    private VisitsSQLDAO visitsDAO;
    private PatientSqlDAO patientDAO;
    private DoctorSqlDAO doctorDAO;

    public PatientCommandsSQL(BasicDataSource dataSource){
        this.dataSource = dataSource;
        visitsDAO = new VisitsSQLDAO(dataSource);
        patientDAO = new PatientSqlDAO(dataSource);
        doctorDAO = new DoctorSqlDAO(dataSource);
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
}
