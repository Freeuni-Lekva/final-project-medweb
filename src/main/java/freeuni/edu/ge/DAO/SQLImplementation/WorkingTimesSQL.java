package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.DAO.Interfaces.WorkingTimesDAOInterface;
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

public class WorkingTimesSQL implements WorkingTimesDAOInterface {

    private static final int NEX_DAYS = 8;
    private static final int DAY_GRAPHIC= 8;

    private final BasicDataSource source;

    public WorkingTimesSQL (BasicDataSource source){
        this.source = source;
    }
//
//    public static void main(String [] args) throws SQLException {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/medweb");
//        dataSource.setUsername("root");
//        dataSource.setPassword("3.1415");
//        WorkingTimesSQL sql = new WorkingTimesSQL(dataSource);
//
//        Doctor doc = new Doctor("123", "123", "123");
//        sql.addDoctor(doc);
//
//        Date date =new Date(2021, 7,16);
//        Time time = new Time(10,0,0);
//        sql.reserveDoctor(doc, date, time);
//        sql.getToString(sql.getAllDoctorWorkingTime());
//
//    }
//
//    public void getToString(Map<String, Map<Date, List<Time>>> returnValue) {
//        for(String ID : returnValue.keySet()) {
//            Map<Date, List<Time>> tmp = returnValue.get(ID);
//            for (Date d : tmp.keySet()) {
//                List<Time> tmp2 = tmp.get(d);
//                System.out.print(d.toString() + " ");
//                for (int i = 0; i < tmp2.size(); i++) {
//                    System.out.print(tmp2.get(i) + " ");
//                }
//                System.out.println("");
//            }
//        }
//    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException {
        source.restart();
        try{
            Connection connection = source.getConnection();
            LocalDateTime localDateTime = LocalDateTime.now();
            for(int i =0; i<NEX_DAYS; i++) {
                PreparedStatement statement = connection.prepareStatement("insert into DoctorWorkTime(ID, Dates, Ten, Eleven, " +
                        "Twelve, Thirteen, Fourteen, Fifteen, Sixteen, Seventeen)" +
                        "values(?,?,?,?,?,?,?,?,?,?)");
                statement.setString(1, doctor.getID());
                Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth()+i);
                String makeDate = "" + now.getYear() + "-" + now.getMonth() + "-" + now.getDate();
                statement.setString(2, makeDate);
                for(int j =0; j< DAY_GRAPHIC; j++) {
                    statement.setBoolean(3+j, true);
                }
                statement.executeUpdate();
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void updateBase() {

    }

    @Override
    public void reserveDoctor(Doctor doctor, Date date, Time time) throws SQLException {
        source.restart();
        try{
            Connection connection = source.getConnection();
            List<String> times = new ArrayList<>();
            times.add("Ten");
            times.add("eleven");
            times.add("Twelve");
            times.add("Thirteen");
            times.add("Fourteen");
            times.add("Fifteen");
            times.add("Sixteen");
            times.add("Seventeen");
            int index = time.getHours() -10;
            String str = times.get(index);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DoctorWorkTime SET " + str + " =? " + "where (ID = ? && Dates = ?);");
            preparedStatement.setBoolean(1, false);
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
