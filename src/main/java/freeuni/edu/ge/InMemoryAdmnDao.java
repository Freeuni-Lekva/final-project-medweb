package freeuni.edu.ge;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InMemoryAdmnDao implements AdministratorDao{
    private List<Request> list;
    private final String ID = "6000111223344";
    private final String Password = "chemikai";

    public InMemoryAdmnDao(){
        list = new ArrayList<>();
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
        Patient p = new Patient();
        p.setName("saxeli");
        p.setAddress("rustaveli");
        p.setCity("Tbilisi");
        p.setDateOfBirth(new Date(System.currentTimeMillis()));
        p.setID(ID);
        p.setSurname("gvari");
        p.setSex("mamakacuri");
        p.setMobileNumber("577777777");
        return p;
    }

    @Override
    public boolean checkIfItIsAdministrator(String ID, String password) {
        return false;
        //return ID.equals(this.ID)&&password.equals(this.Password);
    }

    @Override
    public boolean checkIfItIsPatient(String ID, String password) {
        return true;
    }

    @Override
    public boolean checkIfItIsDoctor(String ID, String password) {
        return false;
    }
}
