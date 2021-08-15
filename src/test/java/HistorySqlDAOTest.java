import freeuni.edu.ge.DAO.HistorySqlDAO;
import freeuni.edu.ge.Models.History;
import freeuni.edu.ge.Models.Visit;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistorySqlDAOTest {
    private BasicDataSource dataSource = new BasicDataSource();

    String PASSWORD = "datoiesimon1!";
    String USER = "root";


    @BeforeEach
    public void init(){
        dataSource.setUrl("jdbc:mysql://localhost:3306/medWeb");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

    }

    @Test
    public void addToHistory() throws SQLException {
        Visit visit = new Visit("23", "24", "Tavis Tkivili", "05.11.1789");


        HistorySqlDAO dao= new HistorySqlDAO(dataSource);
        //History history = new History(visit, "gankurnebulia");
        visit.setDoctorName("nino");
        visit.setDoctorSurName("ninoshvili");

        visit.setPatientName("eka");
        visit.setPatientSurName("bekauri");

        dao.addFinishedVisit(visit, "gankurnebulia");

        assertEquals(visit.getDoctorId(), dao.getDoctorHistory("24").next().getDoctorId());
        assertEquals(visit.getPatientId(), dao.getPatientHistory("23").next().getPatientId());
        assertEquals(visit.getReason(), dao.getPatientHistory("23").next().getReason());


    }

    @Test
    public void getDoctorAndPatientHistory() throws SQLException {
        HistorySqlDAO dao= new HistorySqlDAO(dataSource);

        Visit visit1 = new Visit("23", "24", "Tavis Tkivili", "05.11.1789");

        visit1.setDoctorName("nino");
        visit1.setDoctorSurName("ninoshvili");

        visit1.setPatientName("eka");
        visit1.setPatientSurName("bekauri");

        dao.addFinishedVisit(visit1, "gankurnebulia");

        Visit visit2 = new Visit("21", "24", "Muclis Tkivili", "05.11.1799");

        visit2.setDoctorName("nino");
        visit2.setDoctorSurName("ninoshvili");

        visit2.setPatientName("vaja");
        visit2.setPatientSurName("margvelashvili");

        dao.addFinishedVisit(visit2, "gankurnebulia");

        Visit visit3 = new Visit("21", "27", "simsivne", "07.11.1413");

        visit3.setDoctorName("akaki");
        visit3.setDoctorSurName("wereteli");

        visit3.setPatientName("vaja");
        visit3.setPatientSurName("margvelashvili");

        dao.addFinishedVisit(visit3, "ganukurnebeli");

        Iterator<History> ptIt = dao.getPatientHistory("21");
        Iterator<History> drIt = dao.getDoctorHistory("24");



        assertEquals(ptIt.next().getDoctorId(), "24");
        assertEquals(ptIt.next().getDoctorId(), "27");
        assertEquals(drIt.next().getReason(), "Tavis Tkivili");
        assertEquals(drIt.next().getPatientId(), "21");


    }
}
