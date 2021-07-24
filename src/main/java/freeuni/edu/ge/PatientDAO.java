package freeuni.edu.ge;

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
}
