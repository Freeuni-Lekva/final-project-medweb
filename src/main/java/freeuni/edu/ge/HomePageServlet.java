package freeuni.edu.ge;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("View/homePage.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("submit") != null){
            String id = httpServletRequest.getParameter("ID");
            String password = httpServletRequest.getParameter("pass");
        //admin login
            AdministratorDao dao = getAdministratorDao(httpServletRequest);
            if(dao.checkIfItIsAdministrator(id, password)) {
                httpServletResponse.sendRedirect("http://localhost:8080/admin");
            } else if(dao.checkIfItIsPatient(id, password)) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("id", id);
                httpServletResponse.sendRedirect("http://localhost:8080/loginPT?id=" + id);
            } else if(dao.checkIfItIsDoctor(id, password)) {
                //httpServletResponse.sendRedirect("http://localhost:8080/ekimis servletis saxeli");
            }
        }

        //if username or password is incorrect: setAttribute message on request

    }


    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
