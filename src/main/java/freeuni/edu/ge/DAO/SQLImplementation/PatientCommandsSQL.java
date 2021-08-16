package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.History;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PatientCommandsSQL implements PatientCommands {
    private BasicDataSource dataSource;
    private VisitsSQLDAO visitsDAO;
    private PatientSqlDAO patientDAO;
    private DoctorSqlDAO doctorDAO;

    private HistorySqlDAO historyDAO;

    private WorkingTimesSQL workingTimesSQL;



    public PatientCommandsSQL(BasicDataSource dataSource){
        this.dataSource = dataSource;
        visitsDAO = new VisitsSQLDAO(dataSource);
        patientDAO = new PatientSqlDAO(dataSource);
        doctorDAO = new DoctorSqlDAO(dataSource);

        historyDAO = new HistorySqlDAO(dataSource);

        workingTimesSQL = new WorkingTimesSQL(dataSource);

    }

    @Override
    public Patient getPatientById(String id) throws SQLException {
        return patientDAO.getPatientByIdNumber(id);
    }

    @Override
    public boolean hasVisits(String id, String type) throws SQLException {
        return visitsDAO.hasVisits(id,type);
    }

    @Override
    public Iterator<Visit> getPatientVisitsIterator(String ID, String type) throws SQLException {
        return visitsDAO.getPatientVisitsIterator(ID,type);
    }

    @Override
    public Doctor getDoctorById(String id) throws SQLException {
        return doctorDAO.getDoctorByIdNumber(id);
    }

    @Override
    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    @Override
    public boolean contains(Patient patient) throws SQLException {
        return contains(patient.getID());
    }

    @Override
    public boolean contains(String ID) throws SQLException {
        return patientDAO.getPatientByIdNumber(ID) != null;
    }

    @Override
    public boolean updatePatientInfo(Patient patient) throws SQLException {
        return patientDAO.updatePatientInfo(patient);
    }

    @Override
    public String getPatientIndex(String id) throws SQLException {
        return patientDAO.getPatientIndex(id);
    }

    @Override
    public String getPatientIdByIndex(String index) throws SQLException {
        return patientDAO.getPatientIdByIndex(index);
    }

    @Override
    public void addVisits(Visit visit) throws SQLException {
        visitsDAO.addVisit(visit);
    }

    @Override

    public Iterator<History> getPatientHistory(String index) throws SQLException {
        return historyDAO.getPatientHistory(index);
    }

    @Override
    public void reserveDoctorVisit(Doctor doctor, Date date, Time time) throws SQLException {
        workingTimesSQL.reserveDoctor(doctor, date, time);

    }

//    @Override
//    public Iterator<History> getDoctorHistory(String index) throws SQLException {
//        return historyDAO.getDoctorHistory(index);
//    }

    public Map<String, Map<Date, List<Time>>> getAllDoctorWorkingTime() throws SQLException {
        return workingTimesSQL.getAllDoctorWorkingTime();
    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException {
        workingTimesSQL.addDoctor(doctor);
    }

    @Override
    public List<Doctor> getDoctorByDegreeAndSpecialty(Doctor.DoctorSpecialities specialty, Doctor.Doctor_Qualifications degree) throws SQLException {
        List<Doctor> it = doctorDAO.getDoctorByDegreeAndSpecialty(specialty, degree);
        return it;
    }

    @Override
    public void updateDoctorWorkingTimeBase() throws SQLException {
        workingTimesSQL.updateBase();
    }

    @Override
    public Date stringToDate(String dateInString) {
        int counter=0;
        int firstDelimIndex =0;
        int secondDelimIndex =0;
        for(int i =0; i <dateInString.length(); i++) {
            if(dateInString.charAt(i) =='-') {
                counter ++;
                if(counter ==1) { firstDelimIndex = i; }
                if(counter == 2) { secondDelimIndex=i;}
            }
        }
        int year = Integer.parseInt(dateInString.substring(0,firstDelimIndex));
        int month =Integer.parseInt(dateInString.substring(firstDelimIndex+1, secondDelimIndex));
        int day =Integer.parseInt(dateInString.substring(secondDelimIndex+1));
        Date returnDate = new Date(year,month,day);
        return returnDate;

    }

    @Override
    public Time stringToTime(String s) {
        int counter=0;
        int firstDelimIndex =0;
        int secondDelimIndex =0;
        for(int i =0; i <s.length(); i++) {
            if(s.charAt(i) ==':') {
                counter ++;
                if(counter ==1) { firstDelimIndex = i; }
                if(counter == 2) { secondDelimIndex=i;}
            }
        }
        int hour = Integer.parseInt(s.substring(0,firstDelimIndex));
        int minute =Integer.parseInt(s.substring(firstDelimIndex+1, secondDelimIndex));
        int second =Integer.parseInt(s.substring(secondDelimIndex+1));
        Time returnTime = new Time(hour,minute,second);
        return returnTime;
    }

}
