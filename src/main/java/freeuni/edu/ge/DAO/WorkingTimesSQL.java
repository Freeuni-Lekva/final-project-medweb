package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class WorkingTimesSQL implements WorkingTimesDAOInterface{

    private BasicDataSource source;

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
