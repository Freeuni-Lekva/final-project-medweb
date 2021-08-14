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

public class WorkingTimesSQL implements WorkingTimesDAOInterface{

    private static final int NEX_DAYS = 8;
    private static final int DAY_GRAPHIC= 8;

    private final BasicDataSource source;

    public WorkingTimesSQL (BasicDataSource source){
        this.source = source;
    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException {

    }

    @Override
    public void updateBase() {

    }

    @Override
    public void reserveDoctor(Doctor doctor, Date date, Time time) {

    }
}
