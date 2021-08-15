package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class WorkingTimesSQL implements WorkingTimesDAOInterface{

    private static final int NEX_DAYS = 8;
    private static final int DAY_GRAPHIC= 8;

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
            for(int i =0; i<NEX_DAYS; i++) {
                PreparedStatement statement = connection.prepareStatement("insert into DoctorWorkTime(ID, Dates, Ten, Eleven, " +
                        "Twelve, Thirteen, Fourteen, Fifteen, Sixteen, Seventeen)" +
                        "values(?,?,?,?,?,?,?,?,?,?)");
                statement.setString(1, doctor.getID());
                Date now = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth()+1);
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
    public void updateBase() {

    }

    @Override
    public void reserveDoctor(Doctor doctor, Date date, Time time) {

    }

    @Override
    public Map<String, Map<Date, List<Time>>> getAllDoctorWorkingTime() {
        return null;
    }
}
