package freeuni.edu.ge.DAO;

import org.apache.commons.dbcp2.BasicDataSource;

public class PatientCommandsSQL implements PatientCommands{
    private BasicDataSource dataSource;

    public PatientCommandsSQL(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }
}
