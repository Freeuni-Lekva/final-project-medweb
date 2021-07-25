package freeuni.edu.ge;

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

    public Visit(String patientId, String doctorId, String reason, String date){
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reason = reason;
        this.date = date;
    }

    private String getPatientName(){
        return patientName;
    }
    private String getPatientSurName(){
        return patientSurName;
    }
    private String getDoctorName(){
        return doctorName;
    }
    private String getDoctorSurName(){
        return doctorSurName;
    }
    private String getPatientId(){
        return patientId;
    }
    private String getDoctorId(){
        return doctorId;
    }
    private String getDate(){
        return date;
    }
    private String getReason(){
        return reason;
    }

    private void setPatientName(String name){
        this.patientName = name;
    }
    private void setPatientSurName(String name){
        this.patientSurName = name;
    }
    private void setDoctorName(String s){
        this.doctorName = s;
    }
    private void setDoctorSurName(String s){
        this.doctorSurName = s;
    }
    private void setPatientId(String s){
        this.patientId = s;
    }
    private void setDoctorId(String s){
        this.doctorId = s;
    }
    private void setDate(String s){
        this.date = s;
    }
    private void setReason(String s){
        this.reason = s;
    }
}

