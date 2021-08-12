package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Visit;

import java.util.Iterator;

public interface DoctorCommands {
    Doctor getDoctorById(String id);
    Iterator<Visit> getVisitsIterator(String ID, String type);
    void updateInfo(Doctor doctor);
}
