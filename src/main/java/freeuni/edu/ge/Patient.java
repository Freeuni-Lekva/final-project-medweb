package freeuni.edu.ge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public Patient() { family = new HashMap<>(); }

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



}
