package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.InMemory.AdministratorDao;
import freeuni.edu.ge.DAO.InMemory.DoctorDAO;
import freeuni.edu.ge.DAO.Interfaces.DoctorCommands;
import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.DoctorSqlDAO;
import freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL;
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
import java.sql.Time;
import java.util.Iterator;
import java.util.ArrayList;

public class BookDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("BookOnId");
        httpServletRequest.setAttribute("BookOnId",id);
        if(httpServletRequest.getParameter("filter") != null) {

            try {
                showDoctors(httpServletRequest, httpServletResponse);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(httpServletRequest.getParameter("book") != null) {
            httpServletRequest.getRequestDispatcher("/View/BookDoctor.jsp").forward(httpServletRequest, httpServletResponse);
        }

        if(httpServletRequest.getParameter("timeButton") != null){
            String time = httpServletRequest.getParameter("time");
            System.out.println(time);
            String Date =  httpServletRequest.getParameter("date");
            System.out.println(Date);
            String doctorID =  httpServletRequest.getParameter("DoctorID");
            System.out.println(doctorID);
            String type = httpServletRequest.getParameter("typeSelect");
            System.out.println(type);
        }

    }

    private void bookDoctor(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, SQLException {
        String doctorId = httpServletRequest.getParameter("doctor");
        DoctorCommands dao = (DoctorCommandsSQL) httpServletRequest.getSession().getAttribute("DAO");
        Doctor doctor = dao.getDoctorById(doctorId);

        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("id");

        Patient patient = dao.getPatientById(id);

        Visit visit = new Visit(id, doctorId, "gamokvleva", "26 maisi");

        //book visit

        session.setAttribute("id", id);

        httpServletResponse.sendRedirect("http://localhost:8080/loginPT?id=" + id);
    }

    private void showDoctors(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException, SQLException {
        PatientCommands dao = (PatientCommandsSQL)httpServletRequest.getSession().getAttribute("DAO");
        Doctor.DoctorSpecialities specialty = Doctor.DoctorSpecialities.valueOf(httpServletRequest.getParameter("specialty"));
        Doctor.Doctor_Qualifications degree = Doctor.Doctor_Qualifications.valueOf(httpServletRequest.getParameter("degree"));

        Iterator<Doctor> filtered = dao.getDoctorByDegreeAndSpecialty(specialty, degree);

        httpServletRequest.setAttribute("doctors", filtered);
        httpServletRequest.setAttribute("specialty", specialty);
        httpServletRequest.setAttribute("degree", degree);

        sendTo(httpServletRequest, httpServletResponse, "View/BookDoctor.jsp");
    }


    private void sendTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String address) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(address).forward(httpServletRequest,httpServletResponse);
    }
}
