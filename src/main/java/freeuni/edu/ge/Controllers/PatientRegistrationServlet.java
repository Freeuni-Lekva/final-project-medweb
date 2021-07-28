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
            tmp = true;
            httpServletRequest.setAttribute("Optional", true);
            httpServletRequest.getRequestDispatcher("View/ForPatientRegistration.jsp").forward(httpServletRequest,httpServletResponse);
        }

        if(httpServletRequest.getParameter("submit") != null) {
            String firstName = httpServletRequest.getParameter("fName");
            String lastName = httpServletRequest.getParameter("lName");
            String id = httpServletRequest.getParameter("ID");
            String password = httpServletRequest.getParameter("password");
            String date = httpServletRequest.getParameter("birthday");
            String city = httpServletRequest.getParameter("city");
            String sex = httpServletRequest.getParameter("sex");
            String address = httpServletRequest.getParameter("address");
            String mobileNumber = httpServletRequest.getParameter("mNumber");
            String diseases = httpServletRequest.getParameter("diseases");
            String alergies = httpServletRequest.getParameter("alergies");

        PatientDAOInterface base = getDAO(httpServletRequest);
        AdministratorDao adminDao = getAdministratorDao(httpServletRequest);
        Map<String, String> map = base.getAllLoginAndPass();
            if (map.containsKey(id)) {
                System.out.println("YES");
                httpServletRequest.setAttribute("Registered",true);
                httpServletRequest.getRequestDispatcher("/View/ForPatientRegistration.jsp").forward(httpServletRequest, httpServletResponse);
            } else {
                Patient patient = new Patient();
                patient.setName(firstName);
                patient.setSurname(lastName);
                patient.setID(id);
                patient.setPassword(password);
                patient.setDateOfBirth(date);
                patient.setCity(city);
                patient.setSex(sex);
                patient.setAddress(address);
                patient.setMobileNumber(mobileNumber);
                patient.setDiseases(diseases);
                patient.setAlergies(alergies);
                Map<String, String> family = new HashMap<>();
                if (tmp) {
                    String fatherID = httpServletRequest.getParameter("fID");
                    String motherID = httpServletRequest.getParameter("mID");

                    family.put("father", fatherID);
                    family.put("mother", motherID);
                }
                base.addPatient(patient);
                adminDao.setPatientOnId(id,patient);
                httpServletRequest.getRequestDispatcher("/View/SuccessfulRegistered.jsp").forward(httpServletRequest, httpServletResponse);

            }
        }

    }

    private PatientDAOInterface getDAO(HttpServletRequest httpServletRequest) {
        PatientDAOInterface returnDAO  = (PatientDAOInterface) httpServletRequest.getServletContext().getAttribute("PatientsBase");
        return returnDAO;
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
