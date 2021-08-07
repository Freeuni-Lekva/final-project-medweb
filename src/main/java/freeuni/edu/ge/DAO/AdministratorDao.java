package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Request;
import freeuni.edu.ge.Models.Visit;

import java.util.Iterator;

public interface AdministratorDao {
  
    void addNewDoctorRegistrationRequest(String name, String surname, String ID);
    Iterator<Request> getIterator();
    Patient getPatientById(String id);
    Doctor getDoctorById(String id);
    boolean checkIfItIsAdministrator(String ID, String password, Hash hash);
    boolean checkIfItIsPatient(String ID, String password, Hash hash);
    boolean checkIfItIsDoctor(String ID, String password, Hash hash);
    void requestAnswer(boolean answer, String ID);
    boolean canDoctorRegister(String name, String surname, String ID);
    void addDoctorPrimaryInformation(String name, String surname, String ID);
    void registrationFinished(Doctor doctor);
    void setPatientOnId(String id, Patient patient);
    void putDoctorById(String id, Doctor doctor);
    String returnDoctorHashingPassword(String id);
    String returnPatientHashingPassword(String id);
    String returnAdministratorHashingPassword(String id);
    Iterator<Visit> getVisitsIterator(String ID, String type);
}
