package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorsDAO implements DoctorDAO{

    private Map<String, Doctor> doctors;

    public DoctorsDAO() {

        doctors = new HashMap<>();
        createDoctors();
    }

    private void createDoctors() {
        Doctor doctor = new Doctor("aleko", "poria", "1111");
        doctor.setQualification(Doctor.Doctor_Qualifications.Doctor_Of_Medicine);
        doctor.setSpeciality(Doctor.DoctorSpecialities.Anesthesiology);
        doctors.put(doctor.getID(), doctor);

        Doctor doctor2 = new Doctor("murtazi", "kankava", "1222");
        doctor2.setQualification(Doctor.Doctor_Qualifications.Bachelor_Of_Medicine);
        doctor2.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        doctors.put(doctor2.getID(), doctor2);

        Doctor doctor3 = new Doctor("ekaterine", "meore", "1333");
        doctor3.setQualification(Doctor.Doctor_Qualifications.Medical_School);
        doctor3.setSpeciality(Doctor.DoctorSpecialities.Anesthesiology);
        doctors.put(doctor3.getID(), doctor3);

        Doctor doctor4 = new Doctor("vasili", "lomachenko", "1555");
        doctor4.setQualification(Doctor.Doctor_Qualifications.Medical_School);
        doctor4.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        doctors.put(doctor4.getID(), doctor4);

        Doctor doctor5 = new Doctor("taya", "poler", "1655");
        doctor5.setQualification(Doctor.Doctor_Qualifications.Master_Of_Medicine);
        doctor5.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        doctors.put(doctor5.getID(), doctor5);
    }

    @Override
    public List<Doctor> getDoctorByDegree(Doctor.Doctor_Qualifications degree) {
        List<Doctor> result = new ArrayList<>();
        for(String id : doctors.keySet()) {
            if(doctors.get(id).getQualification() == degree) {
                result.add(doctors.get(id));
            }
        }
        return result;
    }

    @Override
    public List<Doctor> getDoctorByDegreeAndSpecialty(Doctor.DoctorSpecialities specialty, Doctor.Doctor_Qualifications degree) {
        List<Doctor> res1 = getDoctorByDegree(degree);
        List<Doctor> result = new ArrayList<>();
        for(Doctor doctor : res1) {
            if(doctor.getSpeciality() == specialty) {
                result.add(doctor);
            }
        }
        return result;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(Doctor.DoctorSpecialities specialty) {
        List<Doctor> result = new ArrayList<>();
        for(String id : doctors.keySet()) {
            if(doctors.get(id).getSpeciality() == specialty) {
                result.add(doctors.get(id));
            }
        }
        return result;
    }

    @Override
    public Doctor getDoctorById(String id) {
        return doctors.get(id);
    }
}
