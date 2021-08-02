package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.DAO.PatientDAOInterface;
import freeuni.edu.ge.Models.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            checkFilledFields(httpServletRequest, httpServletResponse, tmp);
        }

    }

    private void refilledExistFields(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Patient patient = makePatientFromInformation(httpServletRequest,false);
        httpServletRequest.setAttribute("fName", patient.getName());
        httpServletRequest.setAttribute("lName", patient.getSurname());
        httpServletRequest.setAttribute("ID", patient.getID());
        if(patient.getPassword() != null) {
            httpServletRequest.setAttribute("password",patient.getPassword());
        } else {
            httpServletRequest.setAttribute("password", "");
        }
        httpServletRequest.setAttribute("birthday", patient.getDateOfBirth());
        httpServletRequest.setAttribute("city", patient.getCity());
        httpServletRequest.setAttribute("sex", patient.getSex());
        httpServletRequest.setAttribute("address", patient.getAddress());
        httpServletRequest.setAttribute("mNumber", patient.getMobileNumber());
        httpServletRequest.setAttribute("diseases", patient.getDiseases());
        httpServletRequest.setAttribute("alergies", patient.getAlergies());
    }

    private void checkFilledFields(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, boolean tmp) throws ServletException, IOException {
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

    private void checkId(String id, HttpServletRequest httpServletRequest, boolean tmp, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AdministratorDao adminDao = getAdministratorDao(httpServletRequest);
        PatientDAOInterface base = getDAO(httpServletRequest);
        Map<String, String> map = base.getAllLoginAndPass();
        if (map.containsKey(id)) {
            httpServletRequest.setAttribute("Registered",true);
            httpServletRequest.getRequestDispatcher("/View/ForPatientRegistration.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            Patient patient = makePatientFromInformation(httpServletRequest , tmp);
            base.addPatient(patient);
            adminDao.setPatientOnId(id,patient);
            httpServletRequest.getRequestDispatcher("/View/SuccessfulRegistered.jsp").forward(httpServletRequest, httpServletResponse);
        }
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

    private PatientDAOInterface getDAO(HttpServletRequest httpServletRequest) {
        PatientDAOInterface returnDAO  = (PatientDAOInterface) httpServletRequest.getServletContext().getAttribute("PatientsBase");
        return returnDAO;
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
