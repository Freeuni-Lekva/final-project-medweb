package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.DAO.Interfaces.GeneralCommands;
import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Models.Administrator;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class GeneralCommandsSQL implements GeneralCommands {
    private RequestSQLDAO requestDAO;
    private DoctorSqlDAO doctorDAO;
    private PatientSqlDAO patientDAO;
    private WorkingTimesSQL timesDAO;

    public GeneralCommandsSQL(BasicDataSource dataSource){
        requestDAO = new RequestSQLDAO(dataSource);
        doctorDAO = new DoctorSqlDAO(dataSource);
        patientDAO = new PatientSqlDAO(dataSource);
        timesDAO = new WorkingTimesSQL(dataSource);
    }

    @Override
    public boolean canDoctorRegister(String name, String surname, String ID) throws SQLException {
        return requestDAO.canDoctorRegister(name,surname,ID);
    }


    @Override
    public Doctor getDoctorById(String id) throws SQLException {
        return doctorDAO.getDoctorByIdNumber(id);
    }

    @Override
    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) throws SQLException {
        requestDAO.addNewDoctorRegistrationRequest(name,surname,ID);
    }

    @Override
    public void registrationFinished(Doctor doctor) throws SQLException {
        doctorDAO.addDoctor(doctor);
    }

    @Override
    public boolean checkIfItIsAdministrator(String ID, String password, Hash hash) {
        Administrator administrator = new Administrator();
        String hashedPassword = administrator.getPassword(ID);
        if(hashedPassword.equals("")) return false;
        String userTry = hash.generateHash(password);
        return hashedPassword.equals(userTry);
    }

    @Override
    public boolean checkIfItIsPatient(String ID, String password, Hash hash) throws SQLException {
        String pass = patientDAO.getPass(ID);
        if(pass.equals("")) return false;
        return pass.equals(hash.generateHash(password));
    }

    @Override
    public boolean checkIfItIsDoctor(String ID, String password, Hash hash) throws SQLException {
        String pass = doctorDAO.getPass(ID);
        if(pass.equals("")) return false;
        return pass.equals(hash.generateHash(password));
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
    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    @Override
    public void addDoctorInWorkingTimesSQL(Doctor doctor) throws SQLException {
        timesDAO.addDoctor(doctor);
    }

}
