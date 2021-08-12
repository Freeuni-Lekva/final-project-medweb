package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;

import java.sql.Time;
import java.util.Date;

public interface WorkingTimesDAOInterface {
    public void addDoctor(Doctor doctor);
    public void updateBase();
    public void reserveDoctor(Doctor doctor, Date date, Time time);

}
