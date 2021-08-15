package freeuni.edu.ge.Controllers;

import freeuni.edu.ge.DAO.Interfaces.AdministratorCommands;
import freeuni.edu.ge.DAO.Interfaces.DoctorCommands;
import freeuni.edu.ge.DAO.Interfaces.GeneralCommands;
import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import freeuni.edu.ge.DAO.SQLImplementation.AdminCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.GeneralCommandsSQL;
import freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL;
import freeuni.edu.ge.Helpers.Hash;
import freeuni.edu.ge.Helpers.HashUsingSHA1;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class HomePageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        setGeneralDaoOnSession(httpServletRequest);
        httpServletRequest.getRequestDispatcher("View/homePage.jsp").forward(httpServletRequest,httpServletResponse);
    }


    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if(httpServletRequest.getParameter("submit") != null){
            String id = httpServletRequest.getParameter("ID");
            String password = httpServletRequest.getParameter("pass");
        //admin login
            GeneralCommands dao = getGeneralDao(httpServletRequest);
            Hash hash = new HashUsingSHA1();
            if(dao.checkIfItIsAdministrator(id, password, hash)) {
                setAdministratorDaoOnSession(httpServletRequest);
                httpServletResponse.sendRedirect("http://localhost:8080/admin");
            } else {
                try {
                    if(dao.checkIfItIsPatient(id, password, hash)) {
                        String index = setPatientDaoOnSession(httpServletRequest, id);
                        httpServletResponse.sendRedirect("http://localhost:8080/loginPT?id=" + index);
                    } else {
                        try {
                            if(dao.checkIfItIsDoctor(id, password, hash)) {
                                setDoctorDaoOnSession(httpServletRequest);
                                httpServletResponse.sendRedirect("http://localhost:8080/loginDc?id=" + id);
                            } else {
                                httpServletRequest.setAttribute("message","ID or Password Is Incorrect!");
                                httpServletRequest.getRequestDispatcher("View/homePage.jsp").forward(httpServletRequest,httpServletResponse);
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private String setPatientDaoOnSession(HttpServletRequest httpServletRequest, String id) throws SQLException {
        HttpSession session = httpServletRequest.getSession();
        BasicDataSource dataSource = (BasicDataSource) httpServletRequest.getServletContext().getAttribute("dataSource");
        PatientCommands dao = new PatientCommandsSQL(dataSource);
        session.setAttribute("DAO",dao);
        return dao.getPatientIndex(id);
    }


    private GeneralCommands getGeneralDao(HttpServletRequest request){
        GeneralCommands dao = (GeneralCommandsSQL) request.getSession().getAttribute("DAO");
        return dao;
    }


    private void setGeneralDaoOnSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        BasicDataSource dataSource = (BasicDataSource) httpServletRequest.getServletContext().getAttribute("dataSource");
        GeneralCommands dao = new GeneralCommandsSQL(dataSource);
        session.setAttribute("DAO",dao);
    }

    private void setDoctorDaoOnSession(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        BasicDataSource dataSource = (BasicDataSource) httpServletRequest.getServletContext().getAttribute("dataSource");
        DoctorCommands dao = new DoctorCommandsSQL(dataSource);
        session.setAttribute("DAO",dao);
    }

    private void setAdministratorDaoOnSession(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        BasicDataSource dataSource = (BasicDataSource) httpServletRequest.getServletContext().getAttribute("dataSource");
        AdministratorCommands administratorCommands = new AdminCommandsSQL(dataSource);
        session.setAttribute("DAO",administratorCommands);
    }
}
