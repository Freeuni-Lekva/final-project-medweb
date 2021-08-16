package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.Interfaces.DoctorCommands;
import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("tp").equals("p")){
            PatientCommands dao = (PatientCommandsSQL)httpServletRequest.getSession().getAttribute("DAO");
            httpServletRequest.setAttribute("senderID", httpServletRequest.getParameter("patient")); //user type
            httpServletRequest.setAttribute("recipientID", httpServletRequest.getParameter("doctor"));
            try {
                httpServletRequest.setAttribute("user", dao.getPatientById(httpServletRequest.getParameter("patient")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            httpServletRequest.setAttribute("type", "patient");
        }

        if(httpServletRequest.getParameter("tp").equals("d")){
            DoctorCommands dao = (DoctorCommandsSQL)httpServletRequest.getSession().getAttribute("DAO");
            httpServletRequest.setAttribute("senderID", httpServletRequest.getParameter("doctor")); //user type
            httpServletRequest.setAttribute("recipientID", httpServletRequest.getParameter("patient"));
            try {
                httpServletRequest.setAttribute("user", dao.getDoctorById(httpServletRequest.getParameter("doctor")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            httpServletRequest.setAttribute("type", "doctor");
        }

        httpServletRequest.getRequestDispatcher("/View/Chat.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
