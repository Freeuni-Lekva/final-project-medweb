package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL;
import freeuni.edu.ge.Models.Patient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("id");

        httpServletRequest.setAttribute("id", id);

        sendTo(httpServletRequest, httpServletResponse, "View/loginPatient.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("id");
        if(httpServletRequest.getParameter("update") != null) {
            sendInUpdateSite(httpServletRequest, httpServletResponse, id);
        } else if(httpServletRequest.getParameter("logOut") != null) {
            httpServletResponse.sendRedirect("http://localhost:8080/home");
        } else {
            try {
                updatePatientInformation(httpServletRequest, httpServletResponse, session, id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void updatePatientInformation(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpSession session, String id) throws ServletException, IOException, SQLException {
        PatientCommands dao = (PatientCommandsSQL) httpServletRequest.getSession().getAttribute("DAO");
        Patient patient = dao.getPatientById(id);
        updatePatient(patient, httpServletRequest);
        session.setAttribute("id", id);
        httpServletRequest.setAttribute("id", id);
        dao.updatePatientInfo(patient);
        sendTo(httpServletRequest, httpServletResponse, "View/loginPatient.jsp");
    }

    private void updatePatient(Patient patient, HttpServletRequest httpServletRequest) {
        patient.setName(httpServletRequest.getParameter("name"));
        patient.setSurname(httpServletRequest.getParameter("surname"));
        patient.setSex(httpServletRequest.getParameter("sex"));
        patient.setCity(httpServletRequest.getParameter("city") );
        patient.setID(httpServletRequest.getParameter("idNum"));
        patient.setAddress(httpServletRequest.getParameter("address"));
        patient.setMobileNumber(httpServletRequest.getParameter("mobile"));
    }

    private void sendInUpdateSite(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String id) throws ServletException, IOException {
        httpServletRequest.setAttribute("id", id);
        sendTo(httpServletRequest, httpServletResponse, "View/updatePersonalInformationPatient.jsp");
    }

    private void sendTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String address) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(address).forward(httpServletRequest,httpServletResponse);
    }

}
