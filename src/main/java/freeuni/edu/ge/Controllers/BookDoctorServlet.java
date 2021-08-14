package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.DAO.DoctorDAO;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.Models.Visit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BookDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("filter") != null) {

            showDoctors(httpServletRequest, httpServletResponse);

        } else if(httpServletRequest.getParameter("choose") != null) {

            bookDoctor(httpServletRequest, httpServletResponse);

        } else {

            sendTo(httpServletRequest, httpServletResponse, "View/BookDoctor.jsp");

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
