import freeuni.edu.ge.DAO.SQLImplementation.DoctorSqlDAO;
import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Helpers.HashUsingSHA1;
import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorSqlDAOTest {
    private BasicDataSource dataSource = new BasicDataSource();

    String PASSWORD = "datoiesimon1!";
    String USER = "root";


    @BeforeEach
    public void init(){
        dataSource.setUrl("jdbc:mysql://localhost:3306/medWeb");
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setUsername("root");
        dataSource.setPassword("3.1415");

        dataSource.setUsername("root");
        dataSource.setPassword("lukakapa1213");

    }



    // add doctor
    @Test
    public void test1() throws SQLException {
        //System.out.println("Connected");


        DoctorSqlDAO dao = new DoctorSqlDAO(dataSource);

        String name = "luka";
        String surname = "kapanadze";
        String ID = "60931156789";
        String Password = "123";
        String City = "Kutaisi";
        String mobileNumber = "564120689";
        String yearExperience = "7";
        String yearGraduation = "6";

        Doctor dr = new Doctor(name, surname, ID);


        dr.setPassword(Password);
        dr.setCity(City);
        dr.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        dr.setQualification(Doctor.Doctor_Qualifications.Master_Of_Medicine);
        dr.setMobileNumber(mobileNumber);
        dr.setYearExperience(yearExperience);
        dr.setYearGraduation(yearGraduation);

        dao.addDoctor(dr);

        assertEquals(dao.getDoctorByIdNumber(ID).getID(), dr.getID());

        dao.removeDoctor(dr);

    }





    //patient list check
    @Test
    public void test2() throws SQLException {

        DoctorSqlDAO dao = new DoctorSqlDAO(dataSource);


        String name1 = "lasha";
        String surname1 = "bagdavadze";
        String ID1 = "60956789";
        String Password1 = "120A2!";
        String City1 = "Kutaisi";
        String mobileNumber1 = "564120689";
        String yearExperience1 = "2";
        String yearGraduation1 = "3";

        Doctor dr1 = new Doctor(name1, surname1, ID1);


        dr1.setPassword(Password1);
        dr1.setCity(City1);
        dr1.setSpeciality(Doctor.DoctorSpecialities.Emergency_Medicine);
        dr1.setQualification(Doctor.Doctor_Qualifications.Medical_School);
        dr1.setMobileNumber(mobileNumber1);
        dr1.setYearExperience(yearExperience1);
        dr1.setYearGraduation(yearGraduation1);

        String name2 = "luka";
        String surname2 = "kapanadze";
        String ID2 = "60931156789";
        String Password2 = "123";
        String City2 = "Kutaisi";
        String mobileNumber2 = "564120689";
        String yearExperience2 = "7";
        String yearGraduation2 = "6";

        Doctor dr2 = new Doctor(name2, surname2, ID2);

        dr2.setPassword(Password2);
        dr2.setCity(City2);
        dr2.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        dr2.setQualification(Doctor.Doctor_Qualifications.Master_Of_Medicine);
        dr2.setMobileNumber(mobileNumber2);
        dr2.setYearExperience(yearExperience2);
        dr2.setYearGraduation(yearGraduation2);

        dao.addDoctor(dr1);
        dao.addDoctor(dr2);

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(dr1);
        doctors.add(dr2);

        for (int i = 0; i < dao.getDoctors().size(); i++){
            assertEquals(dao.getDoctors().get(i).getID(), doctors.get(i).getID());
        }

    }

    //remove check
    @Test
    public void test3() throws SQLException {

        DoctorSqlDAO dao = new DoctorSqlDAO(dataSource);


        String name1 = "lasha";
        String surname1 = "bagdavadze";
        String ID1 = "60956789";
        String Password1 = "120A2!";
        String City1 = "Kutaisi";
        String mobileNumber1 = "564120689";
        String yearExperience1 = "2";
        String yearGraduation1 = "3";

        Doctor dr1 = new Doctor(name1, surname1, ID1);


        dr1.setPassword(Password1);
        dr1.setCity(City1);
        dr1.setSpeciality(Doctor.DoctorSpecialities.Emergency_Medicine);
        dr1.setQualification(Doctor.Doctor_Qualifications.Medical_School);
        dr1.setMobileNumber(mobileNumber1);
        dr1.setYearExperience(yearExperience1);
        dr1.setYearGraduation(yearGraduation1);

        String name2 = "luka";
        String surname2 = "kapanadze";
        String ID2 = "60931156789";
        String Password2 = "123";
        String City2 = "Kutaisi";
        String mobileNumber2 = "564120689";
        String yearExperience2 = "7";
        String yearGraduation2 = "6";

        Doctor dr2 = new Doctor(name2, surname2, ID2);

        dr2.setPassword(Password2);
        dr2.setCity(City2);
        dr2.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        dr2.setQualification(Doctor.Doctor_Qualifications.Master_Of_Medicine);
        dr2.setMobileNumber(mobileNumber2);
        dr2.setYearExperience(yearExperience2);
        dr2.setYearGraduation(yearGraduation2);

        dao.addDoctor(dr1);
        dao.addDoctor(dr2);



        List<Doctor> doctors = new ArrayList<>();
        doctors.add(dr1);
        doctors.add(dr2);


        assertEquals(dao.getDoctors().size(), doctors.size());

        dao.removeDoctor(dr1);
        doctors.remove(dr1);
        assertEquals(dao.getDoctors().size(), doctors.size());

        dao.removeDoctor(dr2);
        doctors.remove(dr2);
        assertEquals(dao.getDoctors().size(), doctors.size());

    }

    //update check error aqvs
    @Test
    public void test4() throws SQLException {

        DoctorSqlDAO dao = new DoctorSqlDAO(dataSource);




        String name1 = "luka";
        String surname1 = "kapanadze";
        String ID1 = "60931156789";
        String Password1 = "123";
        String City1 = "Kutaisi";
        String mobileNumber1 = "564120689";
        String yearExperience1 = "7";
        String yearGraduation1 = "6";

        Doctor dr1 = new Doctor(name1, surname1, ID1);

        dr1.setPassword(Password1);
        dr1.setCity(City1);
        dr1.setSpeciality(Doctor.DoctorSpecialities.Allergy_Immunology);
        dr1.setQualification(Doctor.Doctor_Qualifications.Master_Of_Medicine);
        dr1.setMobileNumber(mobileNumber1);
        dr1.setYearExperience(yearExperience1);
        dr1.setYearGraduation(yearGraduation1);

        String name2 = "lasha";
        String surname2 = "bagdavadze";
        String ID2 = "60956789";
        String Password2 = "120A2!";
        String City2 = "Kutaisi";
        String mobileNumber2 = "564120689";
        String yearExperience2 = "2";
        String yearGraduation2 = "3";

        Doctor dr2 = new Doctor(name2, surname2, ID2);


        dr2.setPassword(Password2);
        dr2.setCity(City2);
        dr2.setSpeciality(Doctor.DoctorSpecialities.Emergency_Medicine);
        dr2.setQualification(Doctor.Doctor_Qualifications.Medical_School);
        dr2.setMobileNumber(mobileNumber2);
        dr2.setYearExperience(yearExperience2);
        dr2.setYearGraduation(yearGraduation2);

        String name3 = "lado";
        String surname3 = "bagdavadze";
        String ID3 = "60956789";
        String Password3 = "120A2!";
        String City3 = "Tbilisi";
        String mobileNumber3 = "564120689";
        String yearExperience3 = "10";
        String yearGraduation3 = "8";

        Doctor dr3 = new Doctor(name3, surname3, ID3);


        dr3.setPassword(Password3);
        dr3.setCity(City3);
        dr3.setSpeciality(Doctor.DoctorSpecialities.Medical_Generics);
        dr3.setQualification(Doctor.Doctor_Qualifications.Master_Of_Medicine);
        dr3.setMobileNumber(mobileNumber3);
        dr3.setYearExperience(yearExperience3);
        dr3.setYearGraduation(yearGraduation3);

        dao.addDoctor(dr1);



        assertEquals(false, dao.updateDoctorInfo(dr2));

        dao.addDoctor(dr2);

        assertEquals(true, dao.updateDoctorInfo(dr3));

        assertEquals(dao.getDoctorByIdNumber(dr2.getID()).getName(), dr3.getName());
        //assertEquals(dao.getDoctorByIdNumber(dr1.getID()).getQualification(), dr2.getQualification());

    }


    //get Hashed Password Tests
    @Test
    public void test5() throws SQLException {
        Hash hash = new HashUsingSHA1();
        DoctorSqlDAO dao = new DoctorSqlDAO(dataSource);
        Doctor doc = new Doctor("Alexis","Bell","12341231212");
        doc.setPassword("password");
        dao.addDoctor(doc);
        assertEquals(hash.generateHash("password"),dao.getPass("12341231212"));
        assertEquals("",dao.getPass("123456"));

        Doctor doc1 = new Doctor("Kingston", "Martinez","7777");
        String pass = "HardP!ssw0rD";
        doc1.setPassword(pass);
        dao.addDoctor(doc1);
        assertEquals(hash.generateHash(pass),dao.getPass("7777"));
    }

    @Test
    public void test6() throws SQLException {
        DoctorSqlDAO dao = new DoctorSqlDAO(dataSource);
        Doctor doc = new Doctor("ABCD","EFG","1242546");
        doc.setQualification(Doctor.Doctor_Qualifications.Bachelor_Of_Medicine);
        doc.setSpeciality(Doctor.DoctorSpecialities.Dermatology);
        dao.addDoctor(doc);
        Doctor doc2 = new Doctor("ABCD","EFG","12312414124");
        dao.addDoctor(doc2);

        List<Doctor> it = dao.getDoctorByDegreeAndSpecialty(Doctor.DoctorSpecialities.Dermatology, Doctor.Doctor_Qualifications.Bachelor_Of_Medicine);
        int count = 0;
//        while(it.hasNext()){
//            assertEquals(it.next().getID(),"1242546");
//            count++;
//            if(count > 1) assertEquals(false,true);
//        }
    }
}
