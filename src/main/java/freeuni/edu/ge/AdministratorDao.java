package freeuni.edu.ge;

import java.util.Iterator;

public interface AdministratorDao {
  
    void addNewDoctorRegistrationRequest(String name, String surname, String ID);
    Iterator<Request> getIterator();
    Patient getPatientById(String id);
    Doctor getDoctorById(String id);
    boolean checkIfItIsAdministrator(String ID, String password);
    boolean checkIfItIsPatient(String ID, String password);
    boolean checkIfItIsDoctor(String ID, String password);
    void requestAnswer(boolean answer, String ID);
    boolean canDoctorRegister(String name, String surname, String ID);
    void addDoctorPrimaryInformation(String name, String surname, String ID);
    void registrationFinished(Doctor doctor);
}
