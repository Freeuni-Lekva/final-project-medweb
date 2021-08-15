package freeuni.edu.ge.DAO.InMemory;

import freeuni.edu.ge.Models.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientDAO implements PatientDAOInterface {
    private List <Patient> patients = new ArrayList<>();
    Map<String, String> map = new HashMap<>();

    @Override
    public List<Patient> allPatient() {
        return patients;
    }

    @Override
    public Map<String, String> getAllLoginAndPass() {
        return map;
    }

    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
        if(!map.containsKey(patient.getID())) {
            map.put(patient.getID(), patient.getPassword());
        }
    }

    @Override
    public Patient getPatientById(String id) {
        return null;
    }
}
