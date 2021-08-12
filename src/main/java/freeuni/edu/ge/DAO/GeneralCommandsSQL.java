package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Models.Administrator;
import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class GeneralCommandsSQL implements GeneralCommands{
    private RequestDAO requestDAO;
    private DoctorSqlDAO doctorDAO;

    public GeneralCommandsSQL(BasicDataSource dataSource){
        requestDAO = new RequestDAO(dataSource);
        doctorDAO = new DoctorSqlDAO(dataSource);
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
    public boolean checkIfItIsPatient(String ID, String password, Hash hash) {
        return false;
    }

    @Override
    public boolean checkIfItIsDoctor(String ID, String password, Hash hash) throws SQLException {
        String pass = doctorDAO.getPass(ID);
        if(pass.equals("")) return false;
        return pass.equals(hash.generateHash(password));
    }
}
