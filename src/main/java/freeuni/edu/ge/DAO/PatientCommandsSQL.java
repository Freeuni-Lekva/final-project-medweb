package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Iterator;

public class PatientCommandsSQL implements PatientCommands{
    private BasicDataSource dataSource;

    public PatientCommandsSQL(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Patient getPatientById(String id) {
        return null;
    }

    @Override
    public boolean hasVisits(String id) {
        return false;
    }

    @Override
    public Iterator<Visit> getPatientVisitsIterator(String ID, String type) {
        return null;
    }
}
