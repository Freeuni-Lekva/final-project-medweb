package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Iterator;

public class DoctorCommandsSQL implements DoctorCommands{
    private DoctorSqlDAO doctorDAO;
    private VisitsSQLDAO visitsDAO;
    private PatientSqlDAO patientDAO;

    public DoctorCommandsSQL(BasicDataSource dataSource){
        doctorDAO = new DoctorSqlDAO(dataSource);
        visitsDAO = new VisitsSQLDAO(dataSource);
        patientDAO = new PatientSqlDAO(dataSource);
    }

    @Override
    public Doctor getDoctorById(String id) throws SQLException {
        System.out.println("In:  " + doctorDAO.getDoctorByIdNumber(id));
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
}
