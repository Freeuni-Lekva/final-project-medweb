package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

public class GeneralCommandsSQL implements GeneralCommands{
    @Override
    public boolean canDoctorRegister(String name, String surname, String ID) {
        return false;
    }

    @Override
    public void addDoctorPrimaryInformation(String name, String surname, String ID) {

    }

    @Override
    public Doctor getDoctorById(String id) {
        return null;
    }

    @Override
    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) {

    }

    @Override
    public void registrationFinished(Doctor doctor) {

    }
}
