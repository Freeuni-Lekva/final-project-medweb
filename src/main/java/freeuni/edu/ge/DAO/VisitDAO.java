package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Visit;

import java.util.List;

public interface VisitDAO {

    List<Visit> getVisitsByPatient(String patientId);

    void addVisit(String patientId, String doctorId, String reason);
}
