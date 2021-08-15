package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.InMemory.AdministratorDao;
import freeuni.edu.ge.DAO.InMemory.DoctorDAO;
import freeuni.edu.ge.DAO.InMemory.PatientDAO;
import freeuni.edu.ge.DAO.Interfaces.DoctorCommands;
import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.PatientSqlDAO;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String patientID = httpServletRequest.getParameter("patient");
        httpServletRequest.setAttribute("patientID", patientID);
        String doctorID = httpServletRequest.getParameter("doctor");
        httpServletRequest.setAttribute("doctorID", doctorID);
        DoctorCommands doctorCommands = (DoctorCommandsSQL) httpServletRequest.getSession().getAttribute("DAO");
        Visit visit = null;
        try {
            visit = doctorCommands.getVisitByDoctorAndPatientId(doctorID, patientID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        httpServletRequest.setAttribute("visit", visit);
        if(httpServletRequest.getParameter("filter") != null) {

            showDoctors(httpServletRequest, httpServletResponse);

        } else if(httpServletRequest.getParameter("choose") != null) {

            bookDoctor(httpServletRequest, httpServletResponse);

        } else {
            httpServletRequest.getRequestDispatcher("/View/Redirect.jsp").forward(httpServletRequest, httpServletResponse);
        }

    }

    private void bookDoctor(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String doctorId = httpServletRequest.getParameter("doctor");
        DoctorDAO doctorDAO = (DoctorDAO) httpServletRequest.getServletContext().getAttribute("DoctorsDAO");
        Doctor doctor = doctorDAO.getDoctorById(doctorId);

        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("id");

        AdministratorDao dao = (AdministratorDao) httpServletRequest.getServletContext().getAttribute("AdministratorDAO");
        Patient patient = dao.getPatientById(id);

        Visit visit = new Visit(id, doctorId, "gamokvleva", "26 maisi");
        visit.setDoctorName(doctor.getName());

        patient.bookVisit(visit);

        session.setAttribute("id", id);

        httpServletResponse.sendRedirect("http://localhost:8080/loginPT?id=" + id);
    }

    private void showDoctors(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        DoctorDAO doctorDAO = (DoctorDAO) httpServletRequest.getServletContext().getAttribute("DoctorsDAO");
        Doctor.DoctorSpecialities specialty = Doctor.DoctorSpecialities.valueOf(httpServletRequest.getParameter("specialty"));
        Doctor.Doctor_Qualifications degree = Doctor.Doctor_Qualifications.valueOf(httpServletRequest.getParameter("degree"));
        List<Doctor> filtered = doctorDAO.getDoctorByDegreeAndSpecialty(specialty, degree);
        httpServletRequest.setAttribute("doctors", filtered);
        httpServletRequest.setAttribute("specialty", specialty);
        httpServletRequest.setAttribute("degree", degree);
        sendTo(httpServletRequest, httpServletResponse, "View/BookDoctor.jsp");
    }


    private void sendTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String address) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(address).forward(httpServletRequest,httpServletResponse);
    }
}