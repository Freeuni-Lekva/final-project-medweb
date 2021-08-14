package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;

import java.util.Iterator;

public interface PatientCommands {
    Patient getPatientById(String id);
    boolean hasVisits(String id);
    Iterator<Visit> getPatientVisitsIterator(String ID, String type);
}
