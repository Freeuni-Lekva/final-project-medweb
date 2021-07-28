package freeuni.edu.ge.DAO;


import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Models.Administrator;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryAdmnDao implements AdministratorDao {
    private List<Request> list;
    private List<Request> canRegister;
    private List<Doctor> doctors;
    private Map<String, Patient> patients;
    private Administrator administrator = new Administrator();


    public InMemoryAdmnDao(){
        list = new ArrayList<>();
        list.add(new Request("luka","kk","123"));
        list.add(new Request("tka","gg","324"));
        canRegister = new ArrayList<>();
        doctors = new ArrayList<>();
        patients = new HashMap<>();
        requestAnswer(Boolean.TRUE,"123");
    }

    @Override
    public void addNewDoctorRegistrationRequest(String name, String surname, String ID) {
        list.add(new Request(name,surname,ID));
    }

    @Override
    public Iterator<Request> getIterator() {
        return list.listIterator();
    }

    @Override
    public Patient getPatientById(String id) {
        for(String helper: patients.keySet()){
            if(helper.equals(id)) return patients.get(helper);
        }
        return null;
    }

    public void setPatientOnId(String id, Patient patient) {
        patients.put(id, patient);
    }

    public Doctor getDoctorById(String ID) {
        for(Doctor doc : doctors){
            if(doc.getID().equals(ID)) return doc;
        }
        return null;
    }


    public String returnAdministratorHashingPassword(String id){
        return administrator.getPassword(id);

    }

    @Override
    public boolean checkIfItIsAdministrator(String ID, String password, Hash hash) {
        String hashedString = returnAdministratorHashingPassword(ID);
        if(hashedString.equals("")) return false;
        return returnAdministratorHashingPassword(ID).equals(hash.generateHash(password));
    }

    @Override
    public boolean checkIfItIsPatient(String ID, String password, Hash hash) {
        return returnPatientHashingPassword(ID).equals(hash.generateHash(password));
    }

    public String returnPatientHashingPassword(String id){
        Patient patient = getPatientById(id);
        return patient.getPassword();
    }

    public String returnDoctorHashingPassword(String id){
        Doctor doc = getDoctorById(id);
        return doc.getPassword();
    }

    @Override
    public boolean checkIfItIsDoctor(String ID, String password, Hash hash) {
        return returnDoctorHashingPassword(ID).equals(hash.generateHash(password));
    }

    @Override
    public void requestAnswer(boolean answer, String ID) {
        Request save = null;
        for(Request req : list){
            if(req.getID().equals(ID)){
                save = req;
                break;
            }
        }
        list.remove(save);
        if(answer) canRegister.add(save);
    }

    @Override
    public boolean canDoctorRegister(String name, String surname, String ID) {
        return canRegister.contains(new Request(name, surname, ID));
    }

    @Override
    public void addDoctorPrimaryInformation(String name, String surname, String ID) {
            doctors.add(new Doctor(name, surname, ID));
    }

    @Override
    public void registrationFinished(Doctor doctor) {
        Request save = null;
        for(Request request : canRegister) {
            if(request.getID().equals(doctor.getID())){
                save = request;
            }
        }
        canRegister.remove(save);
    }
}
