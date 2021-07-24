package freeuni.edu.ge;


import java.util.*;
import jdk.vm.ci.code.Register;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryAdmnDao implements AdministratorDao{
    private List<Request> list;
    private List<Request> canRegister;
    private final String ID = "6000111223344";
    private final String Password = "chemikai";

    public InMemoryAdmnDao(){
        list = new ArrayList<>();
        list.add(new Request("luka","kk","123"));
        list.add(new Request("tka","gg","324"));
        canRegister = new ArrayList<>();
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
//        Map<String, String> family = (Map<String, String>) new ArrayList<>();
//        family.put("giorgi", "kmari");
//        family.put("eka", "shvili");
//        p.setFamily(family);
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

}
