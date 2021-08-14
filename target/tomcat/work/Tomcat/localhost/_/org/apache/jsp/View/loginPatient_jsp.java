/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-14 12:07:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import freeuni.edu.ge.Models.Patient;
import freeuni.edu.ge.DAO.InMemoryAdmnDao;
import java.util.Map;
import freeuni.edu.ge.DAO.AdministratorDao;
import freeuni.edu.ge.Models.Visit;
import freeuni.edu.ge.Models.Administrator;
import java.util.Iterator;

public final class loginPatient_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    String id = (String) request.getAttribute("id");
    AdministratorDao dao = (AdministratorDao) request.getServletContext().getAttribute("AdministratorDAO");
    Patient patient = dao.getPatientById(id);

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Welcome ");
      out.print(patient.getName());
      out.write("!</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("    <h2>Personal Information</h2>\r\n");
      out.write("    <p>\r\n");
      out.write("        <label>Name: </label> ");
      out.print(patient.getName() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>Surname: </label> ");
      out.print(patient.getSurname() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>Date of birth: </label> ");
      out.print(patient.getDateOfBirth() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>sex: </label> ");
      out.print(patient.getSex() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>City: </label> ");
      out.print(patient.getCity() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>ID: </label> ");
      out.print(patient.getID() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>Address: </label> ");
      out.print(patient.getAddress() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <label>Mobile: </label> ");
      out.print(patient.getMobileNumber() );
      out.write(" <br>\r\n");
      out.write("\r\n");
      out.write("        <form action=\"/loginPT\" method=\"post\">\r\n");
      out.write("            <input type=\"submit\" value = \"Update Personal Information\" name = \"update\"><br>\r\n");
      out.write("            <input type=\"submit\" value = \"Log Out\" name = \"logOut\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </p>\r\n");
      out.write("\r\n");
      out.write("    ");
 if(patient.hasVisits()) {
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <h2>Currently You have no visits booked!</h2>\r\n");
      out.write("\r\n");
      out.write("    ");
} else {
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <h2>Your Physical Visits:</h2>\r\n");
      out.write("\r\n");
      out.write("        <ol>\r\n");
      out.write("            ");
 for(Visit visit : patient.getVisits()) { 
      out.write("\r\n");
      out.write("                <li>Doctor: ");
      out.print(visit.getDoctorId() );
      out.write(" <br> Reason: ");
      out.print(visit.getReason() );
      out.write(" <br> Date: ");
      out.print(visit.getDate() );
      out.write("</li> </br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("        </ol>\r\n");
      out.write("\r\n");
      out.write("        <form action=\"/bookDC\" method=\"post\">\r\n");
      out.write("            <input type=\"submit\" value = \"Book New Visit\" name = \"book\">\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("    ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <h2>Your Online Visits:</h2>\r\n");
      out.write("\r\n");
      out.write("    <ol>\r\n");
      out.write("        ");
 AdministratorDao adminDao = (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
            Iterator<Visit> it = adminDao.getVisitsIterator(patient.getID(),"Online");
            while(it.hasNext()) {
            Visit visit = it.next();
        
      out.write("\r\n");
      out.write("        <form action = \"/chat?tp=p\" method = post>\r\n");
      out.write("            <li>Doctor: ");
      out.print(visit.getDoctorId() );
      out.write(" <br> Reason: ");
      out.print(visit.getReason() );
      out.write(" <br> Date: ");
      out.print(visit.getDate() );
      out.write("\r\n");
      out.write("                <input type = \"hidden\" name = \"doctor\" id = \"doctor\" value = ");
      out.print(visit.getDoctorId());
      out.write(" >\r\n");
      out.write("                <input type = \"hidden\" name = \"patient\" id = \"patient\" value = ");
      out.print(visit.getPatientId());
      out.write(" >\r\n");
      out.write("                <input type = \"submit\" value = \"Open Chat\">\r\n");
      out.write("            </li> </br>\r\n");
      out.write("        </form>\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("    </ol>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <form action=\"/bookDC\" method=\"post\">\r\n");
      out.write("        <input type=\"submit\" value = \"Book New Visit\" name = \"book\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
