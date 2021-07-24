package freeuni.edu.ge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("View/homePage.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("submit") != null){
        //admin login
            AdministratorDao dao = getAdministratorDao(httpServletRequest);
            if(dao.checkIfItIsAdministrator(httpServletRequest.getParameter("ID"),httpServletRequest.getParameter("pass"))) {
                httpServletResponse.sendRedirect("http://localhost:8080/admin");
            }
        }

        //if username or password is incorrect: setAttribute message on request

    }


    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
