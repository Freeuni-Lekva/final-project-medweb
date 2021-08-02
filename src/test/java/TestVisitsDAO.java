import freeuni.edu.ge.DAO.RequestDAO;
import freeuni.edu.ge.DAO.VisitDAO;
import freeuni.edu.ge.DAO.VisitsDAO;
import freeuni.edu.ge.Models.Request;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.sql.SQLException;

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
        VisitDAO visitDAO = new VisitsDAO(dataSource);
        Visit visit = new Visit("11111", "22222", "kbilis tkivili", "26 maisi");
        int result = visitDAO.addVisit(visit);
        assertEquals(1, result);
    }

    @Test
    public void testGetVisits() throws SQLException {
        VisitDAO visitDAO = new VisitsDAO(dataSource);
        Visit visit = new Visit("11111", "22222", "kbilis tkivili", "26 maisi");
        visitDAO.addVisit(visit);

        Iterator<Visit> iterator = visitDAO.getVisitsByPatient("11111");
        assertEquals("22222", iterator.next().getDoctorId());
    }
}
