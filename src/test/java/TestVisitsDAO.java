import freeuni.edu.ge.DAO.VisitsDAO;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class TestVisitsDAO {

    private BasicDataSource dataSource = new BasicDataSource();

    @BeforeEach
    public void getConnection() {
        dataSource.setUrl("jdbc:mysql://localhost:3306/medweb");
        dataSource.setUsername("root");
        dataSource.setPassword("misho99tedy");
    }

    @Test
    public void testAddingVisits() throws SQLException {
        VisitsDAO visitDAO = new VisitsDAO(dataSource);
        Visit visit = new Visit("11111", "22222", "kbilis tkivili", "26 maisi");
        int result = visitDAO.addVisit(visit);
        assertEquals(1, result);
    }

    @Test
    public void testGetVisits() throws SQLException {
        VisitsDAO visitDAO = new VisitsDAO(dataSource);
        Visit visit = new Visit("11111", "22222", "kbilis tkivili", "26 maisi");
        visitDAO.addVisit(visit);

        Iterator<Visit> iterator = visitDAO.getVisitsByPatient("11111");
        assertEquals("22222", iterator.next().getDoctorId());
    }

    @Test
    public void testSearchVisits() throws SQLException {
        VisitsDAO visitDAO = new VisitsDAO(dataSource);
        visitDAO.addVisit(new Visit("11111", "22222", "kbilis tkivili", "26 maisi"));
        visitDAO.addVisit(new Visit("5555", "6666", "fexi", "dekemeberi"));
        visitDAO.addVisit(new Visit("1212", "34343", "muceli", "12 marti"));

        //Searching
        assertEquals("11111", visitDAO.getVisitByPatientAndDoctorId("11111", "22222").getPatientId());
        assertEquals("5555", visitDAO.getVisitByPatientAndDoctorId("5555", "6666").getPatientId());
        assertEquals("6666", visitDAO.getVisitByPatientAndDoctorId("5555", "6666").getDoctorId());
        assertEquals("1212", visitDAO.getVisitByPatientAndDoctorId("1212", "34343").getPatientId());
    }

    @Test
    public void testDeleteVisit() throws SQLException {
        VisitsDAO visitDAO = new VisitsDAO(dataSource);
        visitDAO.addVisit(new Visit("11111", "22222", "kbilis tkivili", "26 maisi"));
        visitDAO.addVisit(new Visit("5555", "6666", "fexi", "dekemeberi"));

        //Checking if added
        assertEquals("11111", visitDAO.getVisitByPatientAndDoctorId("11111", "22222").getPatientId());
        assertEquals("5555", visitDAO.getVisitByPatientAndDoctorId("5555", "6666").getPatientId());

        //Deleting
        visitDAO.deleteVisitByPatientAndDoctorId("11111", "22222");
        visitDAO.deleteVisitByPatientAndDoctorId("5555", "6666");

        //Checking if deleted
        assertEquals(null, visitDAO.getVisitByPatientAndDoctorId("11111", "22222"));
        assertEquals(null, visitDAO.getVisitByPatientAndDoctorId("5555", "6666"));
    }
}
