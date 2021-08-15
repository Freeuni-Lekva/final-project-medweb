package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.InMemory.AdministratorDao;
import freeuni.edu.ge.Models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        httpServletRequest.setAttribute("id", id);
        httpServletRequest.getRequestDispatcher("View/loginDoctor.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        if(httpServletRequest.getParameter("logOut") != null) {
            httpServletResponse.sendRedirect("http://localhost:8080/home");
        } else if(httpServletRequest.getParameter("edit") != null) {
            httpServletRequest.setAttribute("id", id);
            AdministratorDao dao = (AdministratorDao) httpServletRequest.getServletContext().getAttribute("AdministratorDAO");
            Doctor doctor = dao.getDoctorById(id);
            httpServletRequest.setAttribute("doctor", doctor);
            httpServletRequest.getRequestDispatcher("View/editDoctor.jsp").forward(httpServletRequest,httpServletResponse);
        } else {
            update(httpServletRequest, httpServletResponse, id);
        }
    }

    private void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,String id) throws ServletException, IOException {
        AdministratorDao dao = (AdministratorDao) httpServletRequest.getServletContext().getAttribute("AdministratorDAO");
        Doctor doctor = dao.getDoctorById(id);
        doctor.setCity(httpServletRequest.getParameter("city"));
        String docQ = httpServletRequest.getParameter("qualification");
        for(Doctor.Doctor_Qualifications qualification : Doctor.Doctor_Qualifications.values()){
            if(qualification.toString().equals(docQ)){
                doctor.setQualification(qualification);
                break;
            }
        }
        String docS = httpServletRequest.getParameter("speciality");
        for(Doctor.DoctorSpecialities speciality : Doctor.DoctorSpecialities.values()) {
            if(speciality.toString().equals(docS)) {
                doctor.setSpeciality(speciality);
                break;
            }
        }
        doctor.setMobileNumber(httpServletRequest.getParameter("mobile"));
        doctor.setYearExperience(httpServletRequest.getParameter("yearExperience"));
        httpServletRequest.setAttribute("id", id);
        dao.putDoctorById(id, doctor);
        httpServletRequest.getRequestDispatcher("View/loginDoctor.jsp").forward(httpServletRequest,httpServletResponse);
    }
}