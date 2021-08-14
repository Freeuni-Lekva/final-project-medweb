package freeuni.edu.ge.Models;

import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Helpers.HashUsingSHA1;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
    private String name;
    private String surname;
    private String ID;
    private String password;
    private String city;
    private DoctorSpecialities speciality;
    private String mobileNumber;
    private Doctor_Qualifications qualification;
    private String yearExperience;
    private String yearGraduation;
    private List<Visit> visits;
    private Hash hash = new HashUsingSHA1();

    public enum DoctorSpecialities {
        Allergy_Immunology,
        Anesthesiology,
        Dermatology,
        Diagnostic_Radiology,
        Emergency_Medicine,
        Family_Medicine,
        Internal_Medicine,
        Medical_Generics,
        Neurology,
        Nuclear_Medicine,
        Obstetrics_Gynecology,
        Ophthalmology,
        Pathology,
        Pediatrics,
        Physical_Medicine_And_Rehabilitation,
        Preventive_Medicine,
        Psychiatry,
        Radiation_Oncology,
        Surgery,
        Urology,
        Not_Defined
    }

    public enum Doctor_Qualifications{
        Medical_School,
        Bachelor_Of_Medicine,
        Master_Of_Medicine,
        Doctor_Of_Medicine,
        Not_Defined
    }

    //for primary registration
    public Doctor(String name, String surname, String ID){
        this.name = name;
        this.surname= surname;
        this.ID = ID;
        this.city = "Not Defined";
        this.mobileNumber = "Not Defined";
        this.qualification = Doctor_Qualifications.Not_Defined;
        this.yearGraduation = "Not Defined";
        this.yearExperience = "Not Defined";
        this.speciality = DoctorSpecialities.Not_Defined;
        visits = new ArrayList<>();
    }


    public void setPassword(String password) {
        this.password = hash.generateHash(password);
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setSpeciality(DoctorSpecialities field){
        this.speciality = field;
    }
    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }
    public void setQualification(Doctor_Qualifications qualification){
        this.qualification = qualification;
    }
    public void setYearExperience(String yearExperience){
        this.yearExperience = yearExperience;
    }
    public void setYearGraduation(String yearGraduation){
        this.yearGraduation = yearGraduation;
    }

    public String getName() {
        return name;
    }
    public String getSurname(){
        return surname; }
    public String getID() {
        return ID;
    }
    public String getPassword() {
        return password;
    }
    public String getCity() {
        return city;
    }
    public DoctorSpecialities getSpeciality(){
        return speciality;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }
    public Doctor_Qualifications getQualification(){return qualification;}
    public String getYearExperience() {return yearExperience;}
    public String getYearGraduation(){return yearGraduation;}
    public List<Visit> getVisits(){
        return visits;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", speciality=" + speciality +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", qualification=" + qualification +
                ", yearExperience='" + yearExperience + '\'' +
                ", yearGraduation='" + yearGraduation + '\'' +
                '}';
    }
}
