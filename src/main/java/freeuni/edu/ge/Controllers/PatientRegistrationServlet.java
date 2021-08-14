package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.DAO.PatientCommands;
import freeuni.edu.ge.DAO.PatientCommandsSQL;
import freeuni.edu.ge.DAO.PatientDAOInterface;
import freeuni.edu.ge.Models.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class PatientRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("View/ForPatientRegistration.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        boolean tmp = false;
        if(httpServletRequest.getParameter("add") != null) {
            refilledExistFields(httpServletRequest, httpServletResponse);
            tmp = true;
            httpServletRequest.setAttribute("Optional", true);
            httpServletRequest.getRequestDispatcher("View/ForPatientRegistration.jsp").forward(httpServletRequest,httpServletResponse);
        }
        if(httpServletRequest.getParameter("submit") != null) {
            try {
                checkFilledFields(httpServletRequest, httpServletResponse, tmp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private void refilledExistFields(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
 //       Patient patient = makePatientFromInformation(httpServletRequest,false);
        httpServletRequest.setAttribute("fName", httpServletRequest.getParameter("fName"));
        httpServletRequest.setAttribute("lName", httpServletRequest.getParameter("lName"));
        httpServletRequest.setAttribute("ID", httpServletRequest.getParameter("ID"));
        httpServletRequest.setAttribute("password", httpServletRequest.getParameter("password"));
        httpServletRequest.setAttribute("birthday", httpServletRequest.getParameter("birthday"));
        httpServletRequest.setAttribute("city", httpServletRequest.getParameter("city"));
        httpServletRequest.setAttribute("sex", httpServletRequest.getParameter("sex"));
        httpServletRequest.setAttribute("address", httpServletRequest.getParameter("address"));
        httpServletRequest.setAttribute("mNumber", httpServletRequest.getParameter("mNumber"));
        httpServletRequest.setAttribute("diseases", httpServletRequest.getParameter("diseases"));
        httpServletRequest.setAttribute("alergies", httpServletRequest.getParameter("alergies"));
    }

    private Patient makePatientFromInformation(HttpServletRequest httpServletRequest, boolean tmp) {
        Patient patient = new Patient();
        patient.setName(httpServletRequest.getParameter("fName"));
        patient.setSurname(httpServletRequest.getParameter("lName"));
        patient.setID(httpServletRequest.getParameter("ID"));
        patient.setPassword(httpServletRequest.getParameter("password"));
        patient.setDateOfBirth(httpServletRequest.getParameter("birthday"));
        patient.setCity(httpServletRequest.getParameter("city"));
        patient.setSex(httpServletRequest.getParameter("sex"));
        patient.setAddress(httpServletRequest.getParameter("address"));
        patient.setMobileNumber(httpServletRequest.getParameter("mNumber"));
        patient.setDiseases(httpServletRequest.getParameter("diseases"));
        patient.setAlergies(httpServletRequest.getParameter("alergies"));
        Map<String, String> family = new HashMap<>();
        if (tmp) {
            String fatherID = httpServletRequest.getParameter("fID");
            String motherID = httpServletRequest.getParameter("mID");
            family.put("father", fatherID);
            family.put("mother", motherID);
        }
        return patient;
    }

    private void checkFilledFields(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean tmp) throws ServletException, IOException, SQLException {
        if(ifEmptyNecessaryFields(httpServletRequest)){
            String answer = "Fill ";
            if (httpServletRequest.getParameter("fName") == "") {answer += "First Name Field, "; }
            if (httpServletRequest.getParameter("lName")== "") { answer += "Last Name Field, "; }
            if (httpServletRequest.getParameter("ID")== "") { answer += "ID Number Field,"; }
            if (httpServletRequest.getParameter("password")== "") { answer += "Password Field, "; }
            if (httpServletRequest.getParameter("mNumber")== "") {  answer += "Mobile Number Field."; }
            refilledExistFields(httpServletRequest, httpServletResponse);
            httpServletRequest.setAttribute("NotFilled", answer);
            httpServletRequest.getRequestDispatcher("View/ForPatientRegistration.jsp").forward(httpServletRequest,httpServletResponse);
        } else {
            String id = httpServletRequest.getParameter("ID");
            checkId(id, httpServletRequest, tmp, httpServletResponse);
        }
    }

    private boolean ifEmptyNecessaryFields(HttpServletRequest httpServletRequest) {
        if ((httpServletRequest.getParameter("fName") == "") ||
                (httpServletRequest.getParameter("lName") == "") ||
                (httpServletRequest.getParameter("ID") == "") ||
                (httpServletRequest.getParameter("password") == "") ||
                (httpServletRequest.getParameter("mNumber") == "")) {
            return true;
        }
        return false;
    }

    private void checkId(String id, HttpServletRequest httpServletRequest, boolean tmp, HttpServletResponse httpServletResponse) throws ServletException, IOException, SQLException {
        PatientCommands dao = (PatientCommandsSQL) httpServletRequest.getSession().getAttribute("DAO");
        if (dao.contains(id)) {
            httpServletRequest.setAttribute("Registered",true);
            httpServletRequest.getRequestDispatcher("/View/ForPatientRegistration.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            Patient patient = makePatientFromInformation(httpServletRequest , tmp);
            dao.addPatient(patient);
            httpServletRequest.getRequestDispatcher("/View/SuccessfulRegistered.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
