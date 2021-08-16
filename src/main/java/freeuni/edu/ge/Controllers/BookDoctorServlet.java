package freeuni.edu.ge.Controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.util.List;

public class BookDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("BookOnId");
        if(id == null) {
            id = (String) httpServletRequest.getSession().getAttribute("patientIDD");
        }
        if(httpServletRequest.getParameter("choose") != null) {
            httpServletRequest.setAttribute("BookOnId",id);
            httpServletRequest.setAttribute("Optional", true);
            String ID = httpServletRequest.getParameter("doctorr");
            httpServletRequest.setAttribute("ID", ID);
            sendTo(httpServletRequest, httpServletResponse, "View/WorkingTimes.jsp");
        }
        if(httpServletRequest.getParameter("filter") != null){
            httpServletRequest.setAttribute("BookOnId",id);
            try {
                showDoctors(httpServletRequest, httpServletResponse);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if(httpServletRequest.getParameter("book") != null) {
            httpServletRequest.setAttribute("BookOnId",id);
            httpServletRequest.getSession().setAttribute("patientIDD", id);
            httpServletRequest.getRequestDispatcher("/View/BookDoctor.jsp").forward(httpServletRequest, httpServletResponse);
        }

        if(httpServletRequest.getParameter("timeButton") != null){
            httpServletRequest.setAttribute("BookOnId",id);
            String time = httpServletRequest.getParameter("time");
            String Date =  httpServletRequest.getParameter("date");
            String doctorID =  httpServletRequest.getParameter("DoctorID");
            String type = httpServletRequest.getParameter("typeSelect");
            String reason = httpServletRequest.getParameter("reason");
            String patientId = id;
            Visit visit = new Visit(patientId, doctorID, reason, Date, type);
            PatientCommands patientCommands = (PatientCommandsSQL)httpServletRequest.getSession().getAttribute("DAO");

            try {
                patientCommands.addVisits(visit);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                patientCommands.reserveDoctorVisit(patientCommands.getDoctorById(doctorID),
                        patientCommands.stringToDate(Date)
                        , patientCommands.stringToTime(time));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            httpServletRequest.getRequestDispatcher("View/SuccessfulBooked.jsp").forward(httpServletRequest,httpServletResponse);
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

        dao.updateDoctorWorkingTimeBase();
        List<Doctor> filtered = dao.getDoctorByDegreeAndSpecialty(specialty, degree);

        httpServletRequest.setAttribute("doctors", filtered);
        httpServletRequest.setAttribute("specialty", specialty);
        httpServletRequest.setAttribute("degree", degree);
        sendTo(httpServletRequest, httpServletResponse, "View/BookDoctor.jsp");

    }


    private void sendTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String address) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(address).forward(httpServletRequest,httpServletResponse);
    }
}
