package freeuni.edu.ge.DAO.SQLImplementation;

import freeuni.edu.ge.Models.Patient;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientSqlDAO {

    private BasicDataSource dataSource;

    public PatientSqlDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Patient> getPatients() throws SQLException {
        dataSource.restart();
        List<Patient> patients = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from patients;");
            while(result.next()){
               // System.out.println("Added "+ patients.get(0).getName());
                patients.add(convertToPatient(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return patients;
    }

    public Patient getPatientByIdNumber(String ID_NUMBER) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from patients where ID_NUMBER = ?;");
            statement.setString(1, ID_NUMBER);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                return convertToPatient(result);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public String getPatientIdByIndex(String index) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from patients where id = ?;");
            statement.setString(1, index);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                return result.getString("ID_NUMBER");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void addPatient(Patient patient) throws SQLException {
        dataSource.restart();
        if(getPatientByIdNumber(patient.getID())!=null){
            return;
        }
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into patients  (FirstName, LastName, ID_NUMBER, Password, City, " +
                                                                        "  Birth_Date, Sex, Address,  Mobile_Number, Diseases, Alergies)  " +
                                                                        "values (?,?,?,?,?,?,?,?,?,?,?);");
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setString(3, patient.getID());
            statement.setString(4, patient.getPassword());
            statement.setString(5, patient.getCity());
            statement.setString(6, patient.getDateOfBirth());
            statement.setString(7, patient.getSex());
            statement.setString(8, patient.getAddress());
            statement.setString(9, patient.getMobileNumber());
            statement.setString(10, patient.getDiseases());
            statement.setString(11, patient.getAlergies());

            //System.out.println("Added "+patient.getName());
            //ResultSet resultset = statement.executeQuery();
            statement.executeUpdate();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //remove patient
    public void removePatient(Patient patient) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("delete from patients where ID_NUMBER = ?;");
            statement.setString(1, patient.getID());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean updatePatientInfo(Patient patient) throws SQLException {
        dataSource.restart();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE patients SET FirstName = ?, lastName = ?, Password = ?, City = ?, Birth_Date = ?, Sex = ?,\n" +
                                                                    " Address = ?, Mobile_Number = ?, Diseases = ?, Alergies = ? WHERE (ID_NUMBER = ?);");

            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setString(3, patient.getPassword());
            statement.setString(4, patient.getCity());
            statement.setString(5, patient.getDateOfBirth());
            statement.setString(6, patient.getSex());
            statement.setString(7, patient.getAddress());
            statement.setString(8, patient.getMobileNumber());
            statement.setString(9, patient.getDiseases());
            statement.setString(10, patient.getAlergies());
            statement.setString(11, patient.getID());

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

    public String getPatientIndex(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("Select * FROM patients where ID_NUMBER = ?;");
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()) {
            return resultSet.getString("id");
        }
        return null;
    }



    private Patient convertToPatient(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setName(resultSet.getString("FirstName"));
        patient.setSurname(resultSet.getString("LastName"));
        patient.setID(resultSet.getString("ID_NUMBER"));
        patient.setHashedPassword(resultSet.getString("Password"));
        patient.setCity(resultSet.getString("City"));
        patient.setDateOfBirth(resultSet.getString( "Birth_Date"));
        patient.setSex(resultSet.getString("Sex"));
        patient.setAddress(resultSet.getString("Address"));
        patient.setMobileNumber(resultSet.getString("Mobile_Number"));
        patient.setDiseases(resultSet.getString("Diseases"));
        patient.setAlergies(resultSet.getString("Alergies"));
        return patient;
    }

    public String getPass(String id) throws SQLException {
        dataSource.restart();
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select Password from patients where ID_NUMBER = ?;");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            System.out.println("here is");
            return result.getString("Password");
        }

        return "";
    }

}
