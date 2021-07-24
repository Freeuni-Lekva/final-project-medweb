package freeuni.edu.ge;

import java.util.Iterator;

public interface AdministratorDao {
  
    void addNewDoctorRegistrationRequest(String name, String surname, String ID);
    Iterator<Request> getIterator();
    boolean checkIfItIsAdministrator(String ID, String password);
    boolean checkIfItIsPatient(String ID, String password);
    boolean checkIfItIsDoctor(String ID, String password);
}
