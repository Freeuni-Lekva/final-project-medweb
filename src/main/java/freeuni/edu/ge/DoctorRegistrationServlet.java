package freeuni.edu.ge;

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
        if(httpServletRequest.getParameter("submit") != null) {
            String name = httpServletRequest.getParameter("name");
            String surname = httpServletRequest.getParameter("surname");
            String ID = httpServletRequest.getParameter("ID");
            AdministratorDao adminDAO = getAdministratorDao(httpServletRequest);
            adminDAO.addNewDoctorRegistrationRequest(name,surname,ID);
            httpServletRequest.setAttribute("message","Request Was Sent To Administrator, Please Return After Some Time");
            httpServletRequest.getRequestDispatcher("/View/DoctorRegistrationFirstStage.jsp").forward(httpServletRequest,httpServletResponse);
        }
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
