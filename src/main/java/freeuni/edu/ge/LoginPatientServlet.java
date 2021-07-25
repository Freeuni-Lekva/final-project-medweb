package freeuni.edu.ge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginPatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        httpServletRequest.setAttribute("id", id);
        sendTo(httpServletRequest, httpServletResponse, "View/loginPatient.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String id = (String) session.getAttribute("id");
        if(httpServletRequest.getParameter("update") != null) {
            sendInUpdateSite(httpServletRequest, httpServletResponse, id);
        } else {
            //TODO
        }
    }

    private void sendInUpdateSite(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String id) throws ServletException, IOException {
        httpServletRequest.setAttribute("id", id);
        sendTo(httpServletRequest, httpServletResponse, "View/updatePersonalInformationPatient.jsp");
    }

    private void sendTo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String address) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher(address).forward(httpServletRequest,httpServletResponse);
    }

}
