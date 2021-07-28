package freeuni.edu.ge.Models;

import java.util.List;

public class Visit {
    private String patientName;
    private String patientSurName;
    private String doctorName;
    private String doctorSurName;
    private String patientId;
    private String doctorId;
    private String date;
    private String reason;

    public Visit() {}

    public Visit(String patientId, String doctorId, String reason, String date){
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reason = reason;
        this.date = date;
    }

    public String getPatientName(){
        return patientName;
    }
    public String getPatientSurName(){
        return patientSurName;
    }
    public String getDoctorName(){
        return doctorName;
    }
    public String getDoctorSurName(){
        return doctorSurName;
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

    public void setPatientName(String name){
        this.patientName = name;
    }
    public void setPatientSurName(String name){
        this.patientSurName = name;
    }
    public void setDoctorName(String s){
        this.doctorName = s;
    }
    public void setDoctorSurName(String s){
        this.doctorSurName = s;
    }
    public void setPatientId(String s){
        this.patientId = s;
    }
    public void setDoctorId(String s){
        this.doctorId = s;
    }
    public void setDate(String s){
        this.date = s;
    }
    public void setReason(String s){
        this.reason = s;
    }
}

