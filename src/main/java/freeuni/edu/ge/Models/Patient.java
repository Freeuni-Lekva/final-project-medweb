package freeuni.edu.ge.Models;

import java.util.*;

public class Patient {

    private String name;
    private String surname;
    private String ID;
    private String password;
    private String city;
    private String dateOfBirth;
    private String sex;
    private String address;
    private String mobileNumber;
    private String diseases;
    private String alergies;
    private Map<String, String> family;
    private List<Visit> visits;

    public Patient() {
        family = new HashMap<>();
        visits = new ArrayList<>();
        Visit visit = new Visit();
        visit.setDoctorName("Valeri");
        visit.setPatientId(ID);
        visit.setDate("orshabati");
        visit.setReason("kbilis mwvave tkivili");
        Visit visit2 = new Visit();
        visit2.setDoctorName("Eka");
        visit2.setPatientId(ID);
        visit2.setDate("samshabari");
        visit2.setReason("kuchis gadanergva");
        visits.add(visit);
        visits.add(visit2);
    }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname){ this.surname = surname; }
    public void setID(String ID) { this.ID = ID; }
    public void setPassword(String password) { this.password = password; }
    public void setCity(String city) { this.city = city;}
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setSex (String sex) { this.sex=sex; }
    public void setAddress(String address) { this.address=address; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setFamily(Map <String, String> family) {this.family = family; }
    public void setDiseases(String diseases) {this.diseases = diseases; }
    public void setAlergies(String alergies) {this.alergies = alergies; }


    public String getName() { return name; }
    public String getSurname(){return surname; }
    public String getID() { return ID; }
    public String getPassword() { return password; }
    public String getCity() { return city;}
    public String getDateOfBirth() { return dateOfBirth; }
    public String getSex () { return sex; }
    public String getAddress() { return address; }
    public String getMobileNumber() { return mobileNumber; }
    public Map<String, String> getFamily() {return family; }
    public String getDiseases() {return diseases; }
    public String getAlergies() {return alergies; }

    public void bookVisit(Visit visit) {
        visits.add(visit);
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public boolean hasVisits() {
        return visits.isEmpty();
    }


}
