package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdminCommandsSQL;
import freeuni.edu.ge.DAO.AdministratorCommands;
import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Helpers.HashUsingSHA1;
import org.apache.commons.dbcp2.BasicDataSource;

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
            Hash hash = new HashUsingSHA1();
            if(dao.checkIfItIsAdministrator(id, password, hash)) {
                HttpSession session = httpServletRequest.getSession();
                BasicDataSource dataSource = (BasicDataSource) httpServletRequest.getServletContext().getAttribute("dataSource");
                AdministratorCommands administratorCommands = new AdminCommandsSQL(dataSource);
                session.setAttribute("DAO",administratorCommands);
                httpServletResponse.sendRedirect("http://localhost:8080/admin");
            } else if(dao.checkIfItIsPatient(id, password, hash)) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("id", id);
                httpServletResponse.sendRedirect("http://localhost:8080/loginPT?id=" + id);
            } else if(dao.checkIfItIsDoctor(id, password, hash)) {
                httpServletResponse.sendRedirect("http://localhost:8080/loginDc?id=" + id);
            } else {
                httpServletRequest.setAttribute("message","ID or Password Is Incorrect!");
                httpServletRequest.getRequestDispatcher("View/homePage.jsp").forward(httpServletRequest,httpServletResponse);
            }
        }
    }



    private AdministratorDao getAdministratorDao(HttpServletRequest request){
        return (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
    }
}
