package freeuni.edu.ge;

import java.util.Iterator;

public interface AdministratorDao {
    public void addNewDoctorRegistrationRequest(String name, String surname, String ID);
    public Iterator<Request> getIterator();
    public boolean checkIfItIsAdministrator(String ID, String password);
    public boolean checkIfItIsPatient(String ID, String password);
    public boolean checkIfItIsDoctor(String ID, String password);
}
