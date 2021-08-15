import freeuni.edu.ge.DAO.SQLImplementation.VisitsSQLDAO;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Iterator;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVisitsDAO {

    private BasicDataSource dataSource = new BasicDataSource();

    @BeforeEach
    public void getConnection() {
        dataSource.setUrl("jdbc:mysql://localhost:3306/medweb");
        dataSource.setUsername("root");
        dataSource.setPassword("lukakapa1213");
    }

    @Test
    public void testAddingVisits() throws SQLException {
        VisitsSQLDAO visitDAO = new VisitsSQLDAO(dataSource);
        Visit visit = new Visit("11111", "22222", "kbilis tkivili", "26 maisi");
        int result = visitDAO.addVisit(visit);
        assertEquals(1, result);
    }

    @Test
    public void testGetVisits() throws SQLException {
        VisitsSQLDAO visitDAO = new VisitsSQLDAO(dataSource);
        Visit visit = new Visit("11111", "22222", "kbilis tkivili", "26 maisi");
        visitDAO.addVisit(visit);

        Iterator<Visit> iterator = visitDAO.getVisitsByPatient("11111");
        assertEquals("22222", iterator.next().getDoctorId());
    }

    @Test
    public void testSearchVisits() throws SQLException {
        VisitsSQLDAO visitDAO = new VisitsSQLDAO(dataSource);
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
        VisitsSQLDAO visitDAO = new VisitsSQLDAO(dataSource);
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


    @Test
    public void testGetDoctorVisitsIterator() throws SQLException {
        VisitsSQLDAO dao = new VisitsSQLDAO(dataSource);

        Iterator<Visit> it = dao.getDoctorVisitsIterator("123123","Online");
        assertTrue(it == null);

        dao.addVisit(new Visit("1000","6000","tkivili","2021-12-12","Online"));
        dao.addVisit(new Visit("1000","5000","fexis tkivili","2021-1-1","Online"));

        Iterator<Visit> it1 = dao.getDoctorVisitsIterator("6000","Online");

        Visit visit = new Visit("1000","6000","tkivili","2021-12-12","Online");
        int count = 0;
        while(it1.hasNext()){
            count ++;
            Visit visit2 = it1.next();
            visit2.setType("Online");
            assertTrue(visit.equals(visit2));
            if(count > 1) assertTrue(Boolean.FALSE);
        }

        Visit newVisit = new Visit("2000","6000","tavis tkivili","2021-07-06","Online");
        dao.addVisit(newVisit);

        Iterator<Visit> it2 = dao.getDoctorVisitsIterator("6000","Online");

        int countTmp = 0;
        while(it2.hasNext()){
            countTmp++;
            if(countTmp == 1){
                Visit result = it2.next();
                result.setType("Online");
                assertTrue(result.equals(visit));
            }

            if (countTmp == 2){
                Visit result = it2.next();
                result.setType("Online");
                assertTrue(result.equals(newVisit));
            }

            if(countTmp > 2)assertTrue(Boolean.FALSE);
        }
    }


    @Test
    public void VisitsTypeTests() throws SQLException {
        VisitsSQLDAO dao = new VisitsSQLDAO(dataSource);

        Visit visit1 = new Visit("489010491","12389358","motentiloba","2021-08-13","Physical");
        Visit visit2 = new Visit("489010492","12389358","motentiloba","2021-08-13","Online");

        dao.addVisit(visit1);
        dao.addVisit(visit2);

        Iterator<Visit> it = dao.getDoctorVisitsIterator("12389358","Online");

        int count = 0;
        while(it.hasNext()){
            count++;
            Visit result = it.next();
            assertTrue(result.equals(visit2));
            if(count > 1)assertTrue(Boolean.FALSE);
        }

        Iterator<Visit> it1 = dao.getDoctorVisitsIterator("12389358","Physical");

        count = 0;
        while(it1.hasNext()){
            count++;
            Visit result = it1.next();
            assertTrue(result.equals(visit1));
            if(count > 1)assertTrue(Boolean.FALSE);
        }

        Visit visit3 = new Visit("12313523512","34562341211","tavis tkivili","2021-10-12","Online");
        dao.addVisit(visit3);
        Iterator<Visit> it2 = dao.getDoctorVisitsIterator("34562341211","Physical");
        assertTrue(it2 == null);
    }
}