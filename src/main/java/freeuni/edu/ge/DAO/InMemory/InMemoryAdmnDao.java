package freeuni.edu.ge.DAO.InMemory;


import freeuni.edu.ge.DAO.InMemory.AdministratorDao;
import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Models.*;

//import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryAdmnDao implements AdministratorDao {
    private List<Request> list;
    private List<Request> canRegister;
    private Map<String, Patient> patients;
    private Map<String, Doctor> doctors;
    private Administrator administrator = new Administrator();


    public InMemoryAdmnDao(){
        list = new ArrayList<>();
        list.add(new Request("luka","kk","123"));
        list.add(new Request("tka","gg","324"));
        canRegister = new ArrayList<>();
        doctors = new HashMap<>();
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

    public void putDoctorById(String id, Doctor doctor) {
        doctors.put(id, doctor);
    }

    public Doctor getDoctorById(String ID) {
        for(String doc : doctors.keySet()){
            if(doc.equals(ID)) return doctors.get(doc);
        }
        System.out.println("test");
        Doctor doctor = new Doctor("luka", "kk", "123");
        doctor.setCity("Tbilisi");
        return doctor;
    }


    public String returnAdministratorHashingPassword(String id){
        return administrator.getPassword(id);

    }

    @Override
    public Iterator<Visit> getVisitsIterator(String ID,String type) {
        if(ID.equals("6000")) {
            List<Visit> visits = new ArrayList<>();
            Visit visit1 = new Visit("6000", "6001", "tavis tkivili", "05/08/2021", "Online");
            visit1.setDoctorName("Michael");
            Visit visit2 = new Visit("6000", "6002", "tavis tkivili", "06/08/2021", "Online");
            visit2.setDoctorName("Sam");
            Visit visit3 = new Visit("6000", "6003", "xelis tkivili", "07/08/2021", "Online");
            visit3.setDoctorName("Ann");
            visits.add(visit1);
            visits.add(visit2);
            visits.add(visit3);
            return visits.iterator();
        } else {
            List<Visit> visits = new ArrayList<>();
            Visit visit2 = new Visit("6000", "6002", "tavis tkivili", "06/08/2021", "Online");
            visit2.setDoctorName("Sam");
            visits.add(visit2);
            return visits.iterator();
        }
    }

    @Override
    public boolean checkIfItIsAdministrator(String ID, String password, Hash hash) {
        String hashedString = returnAdministratorHashingPassword(ID);
        if(hashedString.equals("")) return false;
        return returnAdministratorHashingPassword(ID).equals(hash.generateHash(password));
    }

    @Override
    public boolean checkIfItIsPatient(String ID, String password, Hash hash) {
        String pass = returnPatientHashingPassword(ID);
        if(pass.equals("")) return false;
        return pass.equals(hash.generateHash(password));
    }

    public String returnPatientHashingPassword(String id){
        Patient patient = getPatientById(id);
        if(patient == null) return "";
        return patient.getPassword();
    }

    public String returnDoctorHashingPassword(String id){
        Doctor doc = getDoctorById(id);
        if(doc == null) return "";
        return doc.getPassword();
    }

    @Override
    public boolean checkIfItIsDoctor(String ID, String password, Hash hash) {
        String pass = returnDoctorHashingPassword(ID);
        if(pass.equals("")) return false;
        return pass.equals(hash.generateHash(password));
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
        Doctor doctor = new Doctor(name, surname, ID);
        doctors.put(doctor.getID(), doctor);
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
        putDoctorById(doctor.getID(), doctor);
    }
}
