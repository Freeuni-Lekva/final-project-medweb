import freeuni.edu.ge.DAO.SQLImplementation.WorkingTimesSQL;
import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WorkingTimesSQLTest {
    private BasicDataSource dataSource = new BasicDataSource();


    public void init() throws SQLException {
        dataSource.setUrl("jdbc:mysql://localhost:3306/medweb");
        dataSource.setUsername("root");
        dataSource.setPassword("3.1415");
        clearAndCreateTable();
    }

    private void clearAndCreateTable() throws SQLException {
        clear();
        create();
    }

    private void create() throws SQLException {
        Connection connection = dataSource.getConnection();
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
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("drop table DoctorWorkTime;");
        preparedStatement.executeUpdate();
    }

    @Test
    public void testAddMethod() throws SQLException {
        init();
        WorkingTimesSQL sql = new WorkingTimesSQL(dataSource);
        Doctor doc = new Doctor("123", "123", "123");
        sql.addDoctor(doc);
        assertTrue(String.valueOf((sql.getAllDoctorWorkingTime()).containsKey(doc.getID())), true);
    }

    @Test
    public void testGetDoctorMethod() throws SQLException {
        init();
        WorkingTimesSQL sql = new WorkingTimesSQL(dataSource);
        Doctor doc = new Doctor("123", "123", "123");
        sql.addDoctor(doc);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        assertTrue(String.valueOf((sql.getDoctorWorkingTime(doc)).containsKey(date)), true);
    }

    @Test
    public void  testReserveDoctor() throws SQLException {
        init();
        WorkingTimesSQL sql = new WorkingTimesSQL(dataSource);
        Doctor doc = new Doctor("123", "123", "123");
        sql.addDoctor(doc);
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = new Date(localDateTime.getYear(), localDateTime.getMonthValue()-1, localDateTime.getDayOfMonth());
        Time time = new Time(10,0,0);
        sql.reserveDoctor(doc, date, time);
        int size = sql.getDoctorWorkingTime(doc).get(date).size();
        assertEquals(size, 7);
    }
}
