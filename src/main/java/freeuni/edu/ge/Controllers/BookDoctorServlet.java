package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.DAO.DoctorDAO;
import freeuni.edu.ge.Models.Doctor;
import freeuni.edu.ge.Models.Patient;

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
        if(httpServletRequest.getParameter("book") == null) {
            DoctorDAO doctorDAO = (DoctorDAO) httpServletRequest.getServletContext().getAttribute("DoctorsDAO");
            Doctor.DoctorSpecialities specialty = Doctor.DoctorSpecialities.valueOf(httpServletRequest.getParameter("specialty"));
            Doctor.Doctor_Qualifications degree = Doctor.Doctor_Qualifications.valueOf(httpServletRequest.getParameter("degree"));

            List<Doctor> filtered = doctorDAO.getDoctorByDegreeAndSpecialty(specialty, degree);
            System.out.println(filtered.size());
            httpServletRequest.setAttribute("doctors", filtered);
            httpServletRequest.setAttribute("specialty", specialty);
            httpServletRequest.setAttribute("degree", degree);
        }
        sendTo(httpServletRequest, httpServletResponse, "View/BookDoctor.jsp");

    }

    private void sendTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String address) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(address).forward(httpServletRequest,httpServletResponse);
    }
}
