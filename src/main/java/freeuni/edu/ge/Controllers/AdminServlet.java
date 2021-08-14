package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.AdministratorCommands;
import freeuni.edu.ge.DAO.AdministratorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AdminServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/View/AdminProfile.jsp").forward(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        AdministratorCommands dao = getAdministratorDao(httpServletRequest);
        String ID = httpServletRequest.getParameter("hidden");

            if (httpServletRequest.getParameter("accept"+ID) != null) {
                try {
                    dao.requestAnswer(Boolean.TRUE, ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (httpServletRequest.getParameter("reject"+ID) != null) {
                try {
                    dao.requestAnswer(Boolean.FALSE, ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            httpServletRequest.getRequestDispatcher("/View/AdminProfile.jsp").forward(httpServletRequest, httpServletResponse);

    }

    private AdministratorCommands getAdministratorDao(HttpServletRequest request){
        return (AdministratorCommands)request.getSession().getAttribute("DAO");
    }

}