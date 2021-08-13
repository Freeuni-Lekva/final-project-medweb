package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Visit;

import java.sql.SQLException;
import java.util.Iterator;

public interface DoctorCommands {
    Doctor getDoctorById(String id) throws SQLException;
    Iterator<Visit> getDoctorVisitsIterator(String ID, String type) throws SQLException;
    void updateInfo(Doctor doctor) throws SQLException;
}
