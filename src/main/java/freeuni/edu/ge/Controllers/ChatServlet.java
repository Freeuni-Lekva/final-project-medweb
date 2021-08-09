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

        if(httpServletRequest.getParameter("tp").equals("p")){
            httpServletRequest.setAttribute("senderID", httpServletRequest.getParameter("patient")); //user type
            httpServletRequest.setAttribute("recipientID", httpServletRequest.getParameter("doctor"));
            httpServletRequest.setAttribute("user", dao.getPatientById(httpServletRequest.getParameter("patient")));
            httpServletRequest.setAttribute("type", "patient");
        }

        if(httpServletRequest.getParameter("tp").equals("d")){
            httpServletRequest.setAttribute("senderID", httpServletRequest.getParameter("doctor")); //user type
            httpServletRequest.setAttribute("recipientID", httpServletRequest.getParameter("patient"));
            httpServletRequest.setAttribute("user", dao.getDoctorById(httpServletRequest.getParameter("doctor")));
            httpServletRequest.setAttribute("type", "doctor");
        }

        httpServletRequest.getRequestDispatcher("/View/Chat.jsp").forward(httpServletRequest, httpServletResponse);
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
