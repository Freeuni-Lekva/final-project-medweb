package freeuni.edu.ge;

import java.util.List;
import java.util.Map;

public interface PatientDAOInterface {
    public List<Patient> allPatient();
    public Map<String, String> getAllLoginAndPass();
    public void addPatient(Patient patient);
}
