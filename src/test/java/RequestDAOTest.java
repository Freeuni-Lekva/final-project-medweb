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




    //checking remove from requests table method
    @Test
    public void test4() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);
        dao.addNewDoctorRegistrationRequest("axali","doxtori","6000100203040");

        assertEquals("axali",dao.getNameById("6000100203040"));
        dao.requestAnswer(Boolean.FALSE,"6000100203040");
        assertEquals("",dao.getNameById("6000100203040"));
        //not Exists
        dao.requestAnswer(Boolean.FALSE,"123455");


        Request request1 = new Request("luka","kapanadze","60001156789");
        dao.requestAnswer(Boolean.FALSE,"60001156789");
        assertEquals("",dao.getNameById("60001156789"));
        assertEquals("",dao.getSurnameById("60001156789"));

        //add after remove

        dao.addNewDoctorRegistrationRequest(request1);
        assertEquals("luka",dao.getNameById("60001156789"));
        assertEquals("kapanadze",dao.getSurnameById("60001156789"));
    }



    //test about can register table insert. method - add new doctor in mentioned table.
    @Test
    public void test5() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);
        Request request1 = new Request("Michael","Butler","30001233445");
        Request request2 = new Request("Nicole","Bennett","60231256419");

        assertEquals(null,dao.getRequestFromCanRegisterTableByID("30001233445"));
        assertEquals(null,dao.getRequestFromCanRegisterTableByID("23232323232"));
        assertEquals(false,dao.canDoctorRegister("Michael","Butler","30001233445"));

        dao.addDoctorToCanRegisterTable(request1);
        dao.addDoctorToCanRegisterTable(request2.getName(),request2.getSurname(),request2.getID());

        assertEquals(request1,dao.getRequestFromCanRegisterTableByID("30001233445"));
        assertEquals(request2,dao.getRequestFromCanRegisterTableByID("60231256419"));
        assertEquals(true,dao.canDoctorRegister("Michael","Butler","30001233445"));

    }



    //when doctor is accepted
    @Test
    public void test6() throws SQLException {
        RequestDAO dao = new RequestDAO(dataSource);

        //in base
        Request request1 = new Request("luka","kapanadze","60001156789");
        Request request2 = new Request("doctor","doctorashvil","10001112233");
        Request request3 = new Request("medic","medicamenti","12341231212");

        dao.requestAnswer(Boolean.TRUE,"12341231212");
        dao.requestAnswer(Boolean.FALSE,"10001112233");
        dao.requestAnswer(Boolean.TRUE,"60001156789");

        assertEquals(request3,dao.getRequestFromCanRegisterTableByID("12341231212"));
        assertEquals(null,dao.getRequestFromCanRegisterTableByID("10001112233"));
        assertEquals(request1,dao.getRequestFromCanRegisterTableByID("60001156789"));
    }



}
