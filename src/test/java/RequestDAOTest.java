import freeuni.edu.ge.DAO.RequestDAO;
import freeuni.edu.ge.Models.Request;
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

import static org.junit.jupiter.api.Assertions.*;

public class RequestDAOTest {

    private BasicDataSource dataSource = new BasicDataSource();


    @BeforeEach
    public void init(){
        dataSource.setUrl("jdbc:mysql://localhost:3306/medweb");
        dataSource.setUsername("root");
        dataSource.setPassword("lukakapa1213");
    }


    //about insert request in table
    @Test
    public void test1() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);
        String name = "luka";
        String surname = "kapanadze";
        String ID = "60001156789";
        dao.addNewDoctorRegistrationRequest(name,surname,ID);
        assertEquals(dao.getNameById(ID),name);
        assertEquals(dao.getSurnameById(ID),surname);
    }


    //get Request method check
    @Test
    public void test2() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);
        String ID = "60001156789";
        Request request = dao.getRequestByID(ID);
        assertEquals(request.getID(), ID);
        assertEquals(request.getName(), "luka");
        assertEquals(request.getSurname(), "kapanadze");
    }

    //Iterator test
    @Test
    public void test3() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);
        Request request1 = new Request("luka","kapanadze","60001156789");
        Request request2 = new Request("doctor","doctorashvil","10001112233");
        Request request3 = new Request("medic","medicamenti","12341231212");
        dao.addNewDoctorRegistrationRequest(request2);
        dao.addNewDoctorRegistrationRequest(request3);
        ArrayList<Request> toCheck = new ArrayList<>();

        toCheck.add(request1);
        toCheck.add(request2);
        toCheck.add(request3);

        Iterator<Request> it = dao.getIterator();
        //here we check also that, table size is exact 3
        for(int i = 0; i < 3; i++){
            assertEquals(true,it.next().equals(toCheck.get(i)));
        }


        boolean isExactThreeSize = true;
        if(it.hasNext()) isExactThreeSize = false;
        assertEquals(true,isExactThreeSize);
    }
}
