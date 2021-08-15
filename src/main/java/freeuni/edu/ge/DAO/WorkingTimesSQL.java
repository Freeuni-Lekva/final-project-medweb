package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;
import java.sql.*;
import java.util.Date;

public class WorkingTimesSQL implements WorkingTimesDAOInterface{

    private static final int NEXT_DAYS = 8;
    private static final int DAY_GRAPHIC= 8;
    private static final int WORK_START_TIME = 10;

    private final BasicDataSource source;

    public WorkingTimesSQL (BasicDataSource source){
        this.source = source;
    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException {
        source.restart();
        try{
            Connection connection = source.getConnection();
            LocalDateTime localDateTime = LocalDateTime.now();
            for(int i =0; i<NEXT_DAYS; i++) {
                PreparedStatement statement = connection.prepareStatement("insert into DoctorWorkTime(ID, Dates, Ten, Eleven, " +
                        "Twelve, Thirteen, Fourteen, Fifteen, Sixteen, Seventeen)" +
                        "values(?,?,?,?,?,?,?,?,?,?)");
                statement.setString(1, doctor.getID());
                Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth()+i);
                String makeDate = "" + now.getYear() + "-" + now.getMonth() + "-" + now.getDate();
                statement.setString(2, makeDate);
                for(int j =0; j< DAY_GRAPHIC; j++) {
                    statement.setBoolean(3+j, false);
                }
                statement.executeUpdate();
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void updateBase() throws SQLException {
        source.restart();
        Map<String, Map<Date, List<Time>>> doctorsInformation = getAllDoctorWorkingTime();
        doctorsInformation = clearBaseAndUpdate(doctorsInformation);
        addInBase(doctorsInformation);
    }

    private void addInBase(Map<String, Map<Date, List<Time>>> doctorsInformation) throws SQLException {
        source.restart();
        try{
            Connection connection = source.getConnection();
            for(String ID : doctorsInformation.keySet()) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into DoctorWorkTime(ID, Dates, Ten, Eleven, " +
                        "Twelve, Thirteen, Fourteen, Fifteen, Sixteen, Seventeen)" +
                        "values(?,?,?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, ID);
                Map<Date, List<Time>> dates = doctorsInformation.get(ID);
                for(Date date : dates.keySet()){
                    String makeDate = "" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate();
                    preparedStatement.setString(2, makeDate);
                    List<Time> times = dates.get(date);
                    for(int i =0; i <NEXT_DAYS; i++) {
                        preparedStatement.setBoolean(3+i,false);
                    }
                    for(int i =0;i<times.size(); i++) {
                        List<String> timesNames = Arrays.asList("Ten","eleven", "Twelve","Thirteen",
                                "Fourteen", "Fifteen","Sixteen","Seventeen" );
                        int index = (times.get(i)).getHours() -10;
                        String str = timesNames.get(index);
                        preparedStatement.setBoolean(2+timesNames.indexOf(str),true);
                    }
                    preparedStatement.executeUpdate();
                }
            }
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Map<String, Map<Date, List<Time>>> clearBaseAndUpdate(Map<String, Map<Date, List<Time>>> doctorsInformation) throws SQLException {
        clearAndCreateBase();
        Map <String, List <Date>> doctorAndDates = new HashMap<>();
        makeCopyOfDoctorsInformation(doctorsInformation, doctorAndDates);
        updateWorkingTimesMap(doctorAndDates);
        Map<String, Map<Date, List<Time>>> tmpToctorsInformation = new HashMap<>();
        for(String doctorID : doctorAndDates.keySet()) {
            List<Date> doctorDates = doctorAndDates.get(doctorID);
            Map<Date, List<Time>> doctorWork = doctorsInformation.get(doctorID);
            Map<Date, List<Time>> tmpDoctorWork = new HashMap<>();
            for(int i=0; i <doctorDates.size();i++) {
                if(!doctorWork.containsKey(doctorDates.get(i))) {
                    List<Time> dayGraphic = new ArrayList<>();
                    tmpDoctorWork.put(doctorDates.get(i), dayGraphic);
                } else {
                    tmpDoctorWork.put(doctorDates.get(i), doctorWork.get(doctorDates.get(i)));
                }
                tmpToctorsInformation.put(doctorID, tmpDoctorWork);
            }
        }
        return tmpToctorsInformation;
    }

    private void updateWorkingTimesMap(Map<String, List<Date>> doctorAndDates) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth());
        for(String doctorID : doctorAndDates.keySet()) {
            List<Date> dates = doctorAndDates.get(doctorID);
            for(int i = 0; i < dates.size(); i++){
                if(dates.get(i).compareTo(now) < 0) {
                    Date date;
                    if(dates.get(dates.size()-1).compareTo(now) < 0) {
                        date = new Date(now.getYear(), now.getMonth(),now.getDate()+1);
                    } else {
                        date = new Date(now.getYear(), now.getMonth(),dates.get(dates.size()-1).getDate()+1);
                    }
                    dates.remove(i);
                    dates.add(date);
                    i--;
                }
            }
        }
    }

    private void makeCopyOfDoctorsInformation(Map<String, Map<Date, List<Time>>> doctorsInformation, Map<String, List<Date>> doctorAndDates) {
        List forDoctorAndDates = new ArrayList<>();
        for(String doctorsId : doctorsInformation.keySet()) {
            Map<Date, List<Time>> eachDoctorTimes = doctorsInformation.get(doctorsId);
            for(Date date : eachDoctorTimes.keySet()) {
                forDoctorAndDates.add(date);
            }
            Collections.sort(forDoctorAndDates);
            doctorAndDates.put(doctorsId,forDoctorAndDates);
        }
    }


    private void clearAndCreateBase() throws SQLException {
        clear();
        createBase();
    }

    private void createBase() throws SQLException {
        Connection connection = source.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("create table DoctorWorkTime(\n" +
                "\tID varchar(40),\n" +
                "    Dates varchar(40),\n" +
                "    Ten boolean,\n" +
                "    Eleven boolean,\n" +
                "    Twelve boolean,\n" +
                "    Thirteen boolean,\n" +
                "    Fourteen boolean,\n" +
                "    Fifteen boolean,\n" +
                "    Sixteen boolean,\n" +
                "    Seventeen boolean\n" +
                ");");
        preparedStatement.executeUpdate();
    }

    private void clear() throws SQLException {
        Connection connection = source.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("drop table DoctorWorkTime;");
        preparedStatement.executeUpdate();
    }

    @Override
    public void reserveDoctor(Doctor doctor, Date date, Time time) throws SQLException {
        source.restart();
        try{
            Connection connection = source.getConnection();
            List<String> times = Arrays.asList("Ten","eleven", "Twelve","Thirteen",
                    "Fourteen", "Fifteen","Sixteen","Seventeen" );
            int index = time.getHours() -10;
            String str = times.get(index);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DoctorWorkTime SET " + str + " =? " + "where (ID = ? && Dates = ?);");
            preparedStatement.setBoolean(1, true);
            preparedStatement.setString(2, doctor.getID());

            String makeDate = "" + date.getYear() + "-" + date.getMonth() + "-" + date.getDate();
            preparedStatement.setString(3,makeDate);
            preparedStatement.executeUpdate();
        }catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Map<String, Map<Date, List<Time>>> getAllDoctorWorkingTime() throws SQLException {
        Map<String, Map<Date, List<Time>>> doctorsWorkingTime = new HashMap<>();
        source.restart();
        try {
            Connection connection = source.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery("select * from DoctorWorkTime;");
            while(resultSet.next()) {
                getInformation(resultSet, doctorsWorkingTime);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return doctorsWorkingTime;
    }

    @Override
    public Map<Date, List<Time>> getDoctorWorkingTime(Doctor doctor) throws SQLException {
        return getAllDoctorWorkingTime().get(doctor.getID());
    }

    private void getInformation(ResultSet resultSet, Map<String, Map<Date, List<Time>>> doctorsWorkingTime) throws SQLException {
        List<Time> times = new ArrayList<>();
        if(resultSet.getBoolean("Ten")){times.add(new Time(10,0,0));}
        if(resultSet.getBoolean("Eleven")){times.add(new Time(11,0,0));}
        if(resultSet.getBoolean("Twelve")){times.add(new Time(12,0,0));}
        if(resultSet.getBoolean("Thirteen")){times.add(new Time(13,0,0));}
        if(resultSet.getBoolean("Fourteen")){times.add(new Time(14,0,0));}
        if(resultSet.getBoolean("Fifteen")){times.add(new Time(15,0,0));}
        if(resultSet.getBoolean("Sixteen")){times.add(new Time(16,0,0));}
        if(resultSet.getBoolean("Seventeen")){times.add(new Time(17,0,0));}

        String dateInString = resultSet.getString("Dates");
        Date date = convertStringToDate(dateInString);
        Map<Date, List<Time>> doctorTimes = new HashMap<>();
        doctorTimes.put(date, times);

        String doctorID = resultSet.getString("ID");
        if(!doctorsWorkingTime.containsKey(doctorID)){
            doctorsWorkingTime.put(doctorID, doctorTimes);
        }else{
            Map<Date, List<Time>> tmp = doctorsWorkingTime.get(doctorID);
            tmp.put(date,times);
            doctorsWorkingTime.put(doctorID, tmp);
        }
    }

    public Date convertStringToDate(String dateInString) {
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
}
