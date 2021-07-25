package freeuni.edu.ge;

public class Doctor {
    private String name;
    private String surname;
    private String ID;
    private String password;
    private String city;
    private String field;
    private String mobileNumber;

    public Doctor(){

    }

    //for primary registration
    public Doctor(String name, String surname, String ID){
        this.name = name;
        this.surname = surname;
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surName){
        this.surname = surname;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setField(String field){
        this.field = field;
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
    public String getField(){
        return field;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }
}
