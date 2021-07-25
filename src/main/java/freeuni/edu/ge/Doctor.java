package freeuni.edu.ge;

public class Doctor {
    private String name;
    private String surname;
    private String ID;
    private String password;
    private String city;
    private String speciality;
    private String mobileNumber;


    enum DoctorSpecialities {
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
        Urology
    }

    //for primary registration
    public Doctor(String name, String surname, String ID){
        this.name = name;
        this.surname= surname;
        this.ID = ID;
    }


    public void setPassword(String password) {
        this.password = password;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setSpeciality(String field){
        this.speciality = field;
    }
    public void setMobileNumber(String mobileNumber){
        this.mobileNumber = mobileNumber;
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
    public String getSpeciality(){
        return speciality;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }
}
