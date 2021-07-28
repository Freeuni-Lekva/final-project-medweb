package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsDAO implements DoctorDAO{

    private Map<String, Doctor> doctors;

    public DoctorsDAO() {
        doctors = new HashMap<>();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(Doctor.DoctorSpecialities specialty) {
        return null;
    }

    @Override
    public Doctor getDoctorById(String id) {
        return doctors.get(id);
    }
}
