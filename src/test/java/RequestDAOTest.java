import freeuni.edu.ge.DAO.RequestDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class RequestDAOTest {

    private BasicDataSource dataSource;


    @Before
    public void init(){
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/forProducts");
        dataSource.setUsername("root");
        dataSource.setPassword("lukakapa1213");
    }


    @Test
    public void simple1() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);
        String name = "luka";
        String surname = "kapanadze";
        String ID = "60001156789";
        dao.addNewDoctorRegistrationRequest(name,surname,ID);
        assertEquals(dao.getNameById(ID),name);
        assertEquals(dao.getSurnameById(ID),surname);
    }
}
