package freeuni.edu.ge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PatientRegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("View/ForPatientRegistration.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        PatientDAO base = getDAO(httpServletRequest);

    }

    private PatientDAO getDAO(HttpServletRequest httpServletRequest) {
        PatientDAO returnDAO  = (PatientDAO) httpServletRequest.getServletContext().getAttribute("PatientsBase");
        return returnDAO;
    }

}
