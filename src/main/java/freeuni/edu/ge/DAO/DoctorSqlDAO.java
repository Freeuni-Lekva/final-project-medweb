package freeuni.edu.ge.DAO;

import freeuni.edu.ge.Models.Doctor;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorSqlDAO {

    private final BasicDataSource dataSource;

    public DoctorSqlDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Doctor> getDoctors() throws SQLException {
        dataSource.restart();
        List<Doctor> doctors = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from doctors;");
            while(result.next()){
                doctors.add(convertToDoctor(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return doctors;
    }



    public Doctor getDoctorByIdNumber(String ID_NUMBER) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from doctors where ID_NUMBER = ?;");
            statement.setString(1, ID_NUMBER);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                return convertToDoctor(result);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        dataSource.restart();
        if(getDoctorByIdNumber(doctor.getID())!=null){
            return;
        }
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into doctors (FirstName, LastName, ID_NUMBER, Password, City, Doctor_Specialities, " +
                                                                        " Mobile_Number, Doctor_Qualifications,  Experience, Graduation)  " +
                                                                        "values (?,?,?,?,?,?,?,?,?,?);");
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getID());
            statement.setString(4, doctor.getPassword());
            statement.setString(5, doctor.getCity());
            statement.setString(6, doctor.getSpeciality().name());
            statement.setString(7, doctor.getMobileNumber());
            statement.setString(8, doctor.getQualification().name());
            statement.setString(9, doctor.getYearExperience());
            statement.setString(10, doctor.getYearGraduation());

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //remove doctor
    public void removeDoctor(Doctor doctor) throws SQLException {
        removeDoctor(doctor.getID());
    }

    public void removeDoctor(String ID) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from doctors where ID_NUMBER = ?;");
            statement.setString(1, ID);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //error aqvs
    public boolean updateDoctorInfo(Doctor doctor) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE doctors SET FirstName = ?, lastName = ?, Password = ?, City = ?, Doctor_Specialities = ?,  \n" +
                    "Mobile_Number = ?, Doctor_Qualifications = ?, Experience = ?, Graduation = ? WHERE (ID_NUMBER = ?);");

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSurname());
            statement.setString(3, doctor.getPassword());
            statement.setString(4, doctor.getCity());
            statement.setString(5, doctor.getSpeciality().name());
            statement.setString(6, doctor.getMobileNumber());
            statement.setString(7, doctor.getQualification().name());
            statement.setString(8, doctor.getYearExperience());
            statement.setString(9, doctor.getYearGraduation());
            statement.setString(10, doctor.getID());

            int ind = statement.executeUpdate();
            if(ind!=1){
                return false;
            }
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    private Doctor convertToDoctor(ResultSet resultSet) throws SQLException {
        Doctor doctor = new Doctor(resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("ID_NUMBER"));
        doctor.setCity(resultSet.getString("City"));
        doctor.setMobileNumber(resultSet.getString("Mobile_Number"));
        doctor.setYearExperience(resultSet.getString("Experience"));
        doctor.setYearGraduation(resultSet.getString("Graduation"));

        String docQualification= resultSet.getString("Doctor_Qualifications");
        for(Doctor.Doctor_Qualifications qualification : Doctor.Doctor_Qualifications.values()){
            if(qualification.toString().equals(docQualification)){
                doctor.setQualification(qualification);
                break;
            }
        }

        String docSpeciality = resultSet.getString("Doctor_Specialities");
        for(Doctor.DoctorSpecialities speciality : Doctor.DoctorSpecialities.values()) {
            if(speciality.toString().equals(docSpeciality)) {
                doctor.setSpeciality(speciality);
                break;
            }
        }

        return doctor;
    }

    public String getPass(String id) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select Password from doctors where ID_NUMBER = ?;");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            return result.getString("Password");
        }

        return "";
    }
}
