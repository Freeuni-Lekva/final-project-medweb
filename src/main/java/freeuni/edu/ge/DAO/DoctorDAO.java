package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getDoctorsBySpecialty(Doctor.DoctorSpecialities specialty);
    List<Doctor> getDoctorByDegree(Doctor.Doctor_Qualifications degree);
    List<Doctor> getDoctorByDegreeAndSpecialty(Doctor.DoctorSpecialities specialty, Doctor.Doctor_Qualifications degree);
    Doctor getDoctorById(String id);
    public void putDoctorById(String id, Doctor doctor);
}
