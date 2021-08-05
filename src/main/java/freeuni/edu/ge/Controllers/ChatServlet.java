package freeuni.edu.ge.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        System.out.println("POST patient ID:  " + httpServletRequest.getParameter("patient"));
        System.out.println("doctor ID: " + httpServletRequest.getParameter("doctor"));
    }
}
