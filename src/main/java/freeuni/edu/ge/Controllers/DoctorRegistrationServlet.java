package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.Models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DoctorRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationFirstStage.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AdministratorDao adminDAO = getAdministratorDao(httpServletRequest);
        if (httpServletRequest.getParameter("submit") != null) {


            String name = httpServletRequest.getParameter("name");
            String surname = httpServletRequest.getParameter("surname");
            String ID = httpServletRequest.getParameter("ID");

            if (adminDAO.canDoctorRegister(name, surname, ID)) {
                if(adminDAO.getDoctorById(ID) == null){
                    adminDAO.addDoctorPrimaryInformation(name,surname,ID);
                }
                httpServletRequest.setAttribute("doctor",adminDAO.getDoctorById(ID));
                httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationSecondStage.jsp").forward(httpServletRequest, httpServletResponse);
            } else {
                adminDAO.addNewDoctorRegistrationRequest(name, surname, ID);
                httpServletRequest.setAttribute("message", "Request Was Sent To Administrator, Please Return After Some Time");
                httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationFirstStage.jsp").forward(httpServletRequest, httpServletResponse);
            }

        }

        if(httpServletRequest.getParameter("register") != null){
            Doctor doc = adminDAO.getDoctorById(httpServletRequest.getParameter("ID"));
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

            adminDAO.registrationFinished(doc);

            httpServletRequest.setAttribute("message","Registration Successfully Completed!");
            httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationSecondStage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }


    private boolean check(Object obj){
        return obj != null;
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
