package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getDoctorsBySpecialty(Doctor.DoctorSpecialities specialty);
    Doctor getDoctorById(String id);
}
