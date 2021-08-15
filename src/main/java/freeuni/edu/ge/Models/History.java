package freeuni.edu.ge.Models;

public class History {

    Visit visit;
    String MedicalConclusion;

    public History(Visit visit, String MedicalConclusion ){
        this.visit = visit;
        this.MedicalConclusion = MedicalConclusion;
    }




    public String getPatientId(){
        return visit.getPatientId();
    }
    public String getDoctorId(){
        return visit.getDoctorId();
    }
    public String getDate(){
        return visit.getDate();
    }
    public String getReason(){
        return visit.getReason();
    }
    public String getType(){
        return visit.getType();
    }

    public String getMedicalConclusion(){
        return MedicalConclusion;
    }

}
