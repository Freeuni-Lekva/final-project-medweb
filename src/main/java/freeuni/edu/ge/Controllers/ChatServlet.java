package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AdministratorDao dao = getAdministratorDao(httpServletRequest);

        httpServletRequest.setAttribute("patientID",httpServletRequest.getParameter("patient")); //user type
        httpServletRequest.setAttribute("doctorID",httpServletRequest.getParameter("doctor"));
        httpServletRequest.setAttribute("user",dao.getPatientById(httpServletRequest.getParameter("patient")));
        httpServletRequest.setAttribute("type","patient");

        httpServletRequest.getRequestDispatcher("/View/Chat.jsp").forward(httpServletRequest, httpServletResponse);
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
