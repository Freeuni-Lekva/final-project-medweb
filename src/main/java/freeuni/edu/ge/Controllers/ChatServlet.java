package freeuni.edu.ge.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setAttribute("patientID",httpServletRequest.getParameter("patient"));
        httpServletRequest.setAttribute("doctorID",httpServletRequest.getParameter("doctor"));

        httpServletRequest.getRequestDispatcher("/View/Chat.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
