package freeuni.edu.ge.Models;

import java.util.List;
import java.util.Objects;

public class Visit {
    private String patientName;
    private String patientSurName;
    private String doctorName;
    private String doctorSurName;
    private String patientId;
    private String doctorId;
    private String date;
    private String reason;
    private String type; //online or physical

    public Visit() {}

    public Visit(String patientId, String doctorId, String reason, String date){
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reason = reason;
        this.date = date;
    }

    //old constructor is deletable
    public Visit(String patientId, String doctorId, String reason, String date, String type){
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reason = reason;
        this.date = date;
        this.type = type;
    }



    public String getPatientId(){
        return patientId;
    }
    public String getDoctorId(){
        return doctorId;
    }
    public String getDate(){
        return date;
    }
    public String getReason(){
        return reason;
    }
    public String getType(){return type;}


    public void setDoctorName(String s){
        this.doctorName = s;
    }
    public void setPatientId(String s){
        this.patientId = s;
    }
    public void setDate(String s){
        this.date = s;
    }
    public void setReason(String s){
        this.reason = s;
    }
    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return Objects.equals(patientId, visit.patientId) && Objects.equals(doctorId, visit.doctorId) && Objects.equals(date, visit.date) && Objects.equals(reason, visit.reason) && Objects.equals(type, visit.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, doctorId, date, reason, type);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

