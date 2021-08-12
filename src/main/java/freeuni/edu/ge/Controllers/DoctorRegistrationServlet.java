package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.DAO.GeneralCommands;
import freeuni.edu.ge.Models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DoctorRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationFirstStage.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        GeneralCommands dao = getAdministratorDao(httpServletRequest);
        if (httpServletRequest.getParameter("submit") != null) {


            String name = httpServletRequest.getParameter("name");
            String surname = httpServletRequest.getParameter("surname");
            String ID = httpServletRequest.getParameter("ID");

            try {
                if (dao.canDoctorRegister(name, surname, ID)) {
    //                if(adminDAO.getDoctorById(ID) == null){
    //                    adminDAO.addDoctorPrimaryInformation(name,surname,ID);
    //                }
                    httpServletRequest.setAttribute("doctor",dao.getDoctorById(ID));
                    httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationSecondStage.jsp").forward(httpServletRequest, httpServletResponse);
                } else {
                    dao.addNewDoctorRegistrationRequest(name, surname, ID);
                    httpServletRequest.setAttribute("message", "Request Was Sent To Administrator, Please Return After Some Time");
                    httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationFirstStage.jsp").forward(httpServletRequest, httpServletResponse);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        if(httpServletRequest.getParameter("register") != null){
//            Doctor doc = adminDAO.getDoctorById(httpServletRequest.getParameter("ID"));
            Doctor doc = new Doctor(httpServletRequest.getParameter("username"),httpServletRequest.getParameter("surname"),httpServletRequest.getParameter("ID"));

            doc.setPassword(httpServletRequest.getParameter("pass"));
            String docSpeciality = httpServletRequest.getParameter("speciality");
            for(Doctor.DoctorSpecialities speciality : Doctor.DoctorSpecialities.values()) {
                if(speciality.toString().equals(docSpeciality)) {
                    doc.setSpeciality(speciality);
                    break;
                }
            }

            if(check(httpServletRequest.getParameter("city"))) doc.setCity(httpServletRequest.getParameter("city"));
            if(check(httpServletRequest.getParameter("mobile"))) doc.setMobileNumber(httpServletRequest.getParameter("mobile"));
            if(check(httpServletRequest.getParameter("qualification"))){
                String docQualification =  httpServletRequest.getParameter("qualification");
                for(Doctor.Doctor_Qualifications qualification : Doctor.Doctor_Qualifications.values()){
                    if(qualification.toString().equals(docQualification)){
                        doc.setQualification(qualification);
                        break;
                    }
                }
            }

            if(check(httpServletRequest.getParameter("experience"))) doc.setYearExperience(httpServletRequest.getParameter("experience"));
            if(check(httpServletRequest.getParameter("graduation"))) doc.setYearGraduation(httpServletRequest.getParameter("graduation"));

            try {
                dao.registrationFinished(doc);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            httpServletRequest.setAttribute("message","Registration Successfully Completed!");
            httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationSecondStage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }


    private boolean check(Object obj){
        return obj != null;
    }

    private GeneralCommands getAdministratorDao(HttpServletRequest request){
        return (GeneralCommands) request.getSession().getAttribute("DAO");
    }
}
