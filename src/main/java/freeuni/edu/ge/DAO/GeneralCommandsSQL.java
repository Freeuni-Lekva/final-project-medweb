package freeuni.edu.ge.DAO;

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
}
