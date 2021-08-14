import freeuni.edu.ge.DAO.PatientSqlDAO;
import freeuni.edu.ge.Models.Patient;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientSqlDAOTest {

    private BasicDataSource dataSource = new BasicDataSource();

    String PASSWORD = "datoiesimon1!";
    String USER = "root";


    @BeforeEach
    public void init(){
        dataSource.setUrl("jdbc:mysql://localhost:3306/medWeb");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

    }



    // add patient
    @Test
    public void test1() throws SQLException {
        //System.out.println("Connected");


        PatientSqlDAO dao = new PatientSqlDAO(dataSource);

        String name = "luka";
        String surname = "kapanadze";
        String ID = "60931156789";
        String Password = "123";
        String City = "Kutaisi";
        String birthDate = "12031980";
        String Sex = "male";
        String Address = "Chavchavadze";
        String mobileNumber = "564120689";
        String disease = "xvela";
        String alergie = "korona";

        Patient pt = new Patient();

        pt.setName(name);
        pt.setSurname(surname);
        pt.setID(ID);
        pt.setPassword(Password);
        pt.setCity(City);
        pt.setDateOfBirth(birthDate);
        pt.setSex(Sex);
        pt.setAddress(Address);
        pt.setMobileNumber(mobileNumber);
        pt.setDiseases(disease);
        pt.setAlergies(alergie);

        dao.addPatient(pt);

        assertEquals(dao.getPatientByIdNumber(ID).getID(), pt.getID());

        //dao.removePatient(pt);

    }





    //patient list check
    @Test
    public void test2() throws SQLException {

        PatientSqlDAO dao = new PatientSqlDAO(dataSource);


        String name1 = "marta";
        String surname1 = "ivanoci";
        String ID1 = "3790";
        String Password1 = "12389";
        String City1 = "Tbiliis";
        String birthDate1 = "12031980";
        String Sex1 = "female";
        String Address1 = "Chavchavadze";
        String mobileNumber1 = "564820189";
        String disease1 = "xvela";
        String alergie1 = "korona";

        Patient pt1 = new Patient();

        pt1.setName(name1);
        pt1.setSurname(surname1);
        pt1.setID(ID1);
        pt1.setPassword(Password1);
        pt1.setCity(City1);
        pt1.setDateOfBirth(birthDate1);
        pt1.setSex(Sex1);
        pt1.setAddress(Address1);
        pt1.setMobileNumber(mobileNumber1);
        pt1.setDiseases(disease1);
        pt1.setAlergies(alergie1);

        String name = "dato";
        String surname = "giorbelidze";
        String ID = "60001147346";
        String Password = "5322";
        String City = "Kutaisi";
        String birthDate = "03062000";
        String Sex = "male";
        String Address = "Chavchavadze";
        String mobileNumber = "574111990";
        String disease = "xgacieba";
        String alergie = "ki";

        Patient pt2 = new Patient();

        pt2.setName(name);
        pt2.setSurname(surname);
        pt2.setID(ID);
        pt2.setPassword(Password);
        pt2.setCity(City);
        pt2.setDateOfBirth(birthDate);
        pt2.setSex(Sex);
        pt2.setAddress(Address);
        pt2.setMobileNumber(mobileNumber);
        pt2.setDiseases(disease);
        pt2.setAlergies(alergie);

        dao.addPatient(pt1);
        dao.addPatient(pt2);



        List<Patient> patients = new ArrayList<>();
        patients.add(pt1);
        patients.add(pt2);

        for (int i = 0; i < dao.getPatients().size(); i++){
            assertEquals(dao.getPatients().get(i).getID(), patients.get(i).getID());
        }

    }

    //remove check
    @Test
    public void test3() throws SQLException {

        PatientSqlDAO dao = new PatientSqlDAO(dataSource);


        String name1 = "meka";
        String surname1 = "beselia";
        String ID1 = "3790892";
        String Password1 = "121139";
        String City1 = "Tbiliis";
        String birthDate1 = "12031980";
        String Sex1 = "female";
        String Address1 = "Chavchavadze";
        String mobileNumber1 = "564820189";
        String disease1 = "xvela";
        String alergie1 = "korona";

        Patient pt1 = new Patient();

        pt1.setName(name1);
        pt1.setSurname(surname1);
        pt1.setID(ID1);
        pt1.setPassword(Password1);
        pt1.setCity(City1);
        pt1.setDateOfBirth(birthDate1);
        pt1.setSex(Sex1);
        pt1.setAddress(Address1);
        pt1.setMobileNumber(mobileNumber1);
        pt1.setDiseases(disease1);
        pt1.setAlergies(alergie1);

        String name2 = "dato";
        String surname2 = "sxvagvari";
        String ID2 = "646";
        String Password2 = "5322";
        String City2 = "Kutaisi";
        String birthDate2 = "03062000";
        String Sex2 = "male";
        String Address2 = "Chavchavadze";
        String mobileNumber2 = "574111990";
        String disease2 = "xgacieba";
        String alergie2 = "ki";

        Patient pt2 = new Patient();

        pt2.setName(name2);
        pt2.setSurname(surname2);
        pt2.setID(ID2);
        pt2.setPassword(Password2);
        pt2.setCity(City2);
        pt2.setDateOfBirth(birthDate2);
        pt2.setSex(Sex2);
        pt2.setAddress(Address2);
        pt2.setMobileNumber(mobileNumber2);
        pt2.setDiseases(disease2);
        pt2.setAlergies(alergie2);

        String name3 = "vigaca";
        String surname3 = "sxvagvari";
        String ID3 = "5926";
        String Password3 = "5322";
        String City3 = "Kutaisi";
        String birthDate3 = "03062000";
        String Sex3 = "male";
        String Address3 = "Chavchavadze";
        String mobileNumber3 = "574111990";
        String disease3 = "xgacieba";
        String alergie3 = "ki";

        Patient pt3 = new Patient();

        pt3.setName(name3);
        pt3.setSurname(surname3);
        pt3.setID(ID3);
        pt3.setPassword(Password3);
        pt3.setCity(City3);
        pt3.setDateOfBirth(birthDate3);
        pt3.setSex(Sex3);
        pt3.setAddress(Address3);
        pt3.setMobileNumber(mobileNumber3);
        pt3.setDiseases(disease3);
        pt3.setAlergies(alergie3);

        dao.addPatient(pt1);
        dao.addPatient(pt2);
        dao.addPatient(pt3);

        List<Patient> patients = new ArrayList<>();
        patients.add(pt1);
        patients.add(pt2);
        patients.add(pt3);

        assertEquals(dao.getPatients().size(), patients.size());

        dao.removePatient(pt1);
        patients.remove(pt1);
        assertEquals(dao.getPatients().size(), patients.size());

        dao.removePatient(pt2);
        patients.remove(pt2);
        assertEquals(dao.getPatients().size(), patients.size());


        dao.removePatient(pt3);
        patients.remove(pt3);
        assertEquals(dao.getPatients().size(), patients.size());

    }

    //update check
    @Test
    public void test4() throws SQLException {

        PatientSqlDAO dao = new PatientSqlDAO(dataSource);


        String name1 = "meka";
        String surname1 = "beselia";
        String ID1 = "3790892";
        String Password1 = "121139";
        String City1 = "Tbiliis";
        String birthDate1 = "12031980";
        String Sex1 = "female";
        String Address1 = "Chavchavadze";
        String mobileNumber1 = "564820189";
        String disease1 = "xvela";
        String alergie1 = "korona";

        Patient pt1 = new Patient();

        pt1.setName(name1);
        pt1.setSurname(surname1);
        pt1.setID(ID1);
        pt1.setPassword(Password1);
        pt1.setCity(City1);
        pt1.setDateOfBirth(birthDate1);
        pt1.setSex(Sex1);
        pt1.setAddress(Address1);
        pt1.setMobileNumber(mobileNumber1);
        pt1.setDiseases(disease1);
        pt1.setAlergies(alergie1);

        String name2 = "dato";
        String surname2 = "sxvagvari";
        String ID2 = "646";
        String Password2 = "5322";
        String City2 = "Kutaisi";
        String birthDate2 = "03062000";
        String Sex2 = "male";
        String Address2 = "Chavchavadze";
        String mobileNumber2 = "574111990";
        String disease2 = "xgacieba";
        String alergie2 = "ki";

        Patient pt2 = new Patient();

        pt2.setName(name2);
        pt2.setSurname(surname2);
        pt2.setID(ID2);
        pt2.setPassword(Password2);
        pt2.setCity(City2);
        pt2.setDateOfBirth(birthDate2);
        pt2.setSex(Sex2);
        pt2.setAddress(Address2);
        pt2.setMobileNumber(mobileNumber2);
        pt2.setDiseases(disease2);
        pt2.setAlergies(alergie2);

        String name3 = "vigaca";
        String surname3 = "sxvagvari";
        String ID3 = "646";
        String Password3 = "5322";
        String City3 = "Kutaisi";
        String birthDate3 = "03062000";
        String Sex3 = "male";
        String Address3 = "Chavchavadze";
        String mobileNumber3 = "574111990";
        String disease3 = "xgacieba";
        String alergie3 = "ki";

        Patient pt3 = new Patient();

        pt3.setName(name3);
        pt3.setSurname(surname3);
        pt3.setID(ID3);
        pt3.setPassword(Password3);
        pt3.setCity(City3);
        pt3.setDateOfBirth(birthDate3);
        pt3.setSex(Sex3);
        pt3.setAddress(Address3);
        pt3.setMobileNumber(mobileNumber3);
        pt3.setDiseases(disease3);
        pt3.setAlergies(alergie3);

        //dao.addPatient(pt1);
        dao.addPatient(pt2);
        //dao.addPatient(pt3);



        assertEquals(false, dao.updatePatientInfo(pt1));
        assertEquals(true, dao.updatePatientInfo(pt3));

        assertEquals(dao.getPatientByIdNumber(pt2.getID()).getName(), pt3.getName());


    }

}
