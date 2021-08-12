package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.sql.SQLException;

public interface GeneralCommands {
    boolean canDoctorRegister(String name, String surname, String ID) throws SQLException;
    void addDoctorPrimaryInformation(String name, String surname, String ID);
    Doctor getDoctorById(String id);
    void addNewDoctorRegistrationRequest(String name, String surname, String ID);
    void registrationFinished(Doctor doctor);
}
