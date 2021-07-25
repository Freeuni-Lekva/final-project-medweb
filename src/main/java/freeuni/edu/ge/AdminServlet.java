package freeuni.edu.ge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/View/AdminProfile.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AdministratorDao adminDAO = getAdministratorDao(httpServletRequest);
        String ID = httpServletRequest.getParameter("hidden");

            if (httpServletRequest.getParameter("accept"+ID) != null) {
                adminDAO.requestAnswer(Boolean.TRUE, ID);
            }

            if (httpServletRequest.getParameter("reject"+ID) != null) {
                adminDAO.requestAnswer(Boolean.FALSE, ID);
            }
            httpServletRequest.getRequestDispatcher("/View/AdminProfile.jsp").forward(httpServletRequest, httpServletResponse);
    }

    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }

}
