/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-16 08:13:06 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import freeuni.edu.ge.Models.Request;
import freeuni.edu.ge.DAO.InMemory.AdministratorDao;
import freeuni.edu.ge.DAO.Interfaces.AdministratorCommands;
import freeuni.edu.ge.DAO.SQLImplementation.AdminCommandsSQL;

public final class AdminProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<title>Admin</title>\r\n");
      out.write("</head>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/Admin.css\" />\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div id = \"header\">\r\n");
      out.write("    <br>\r\n");
      out.write("   <img src = \"resources/ForDesign/Icons8-Windows-8-Users-Administrator-2.ico\" id = \"image\">\r\n");
      out.write("    <br>This is Admin Panel, Please proceed with caution!\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id = \"sidebar\">\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div id = \"data\">\r\n");
      out.write("<h4>See Doctor Registration Requests:</h4>\r\n");
      out.write("\r\n");

    HttpSession adminSession = request.getSession();
    AdministratorCommands dao = (AdminCommandsSQL) adminSession.getAttribute("DAO");
    Iterator<Request> it = dao.getIterator();
    while(it.hasNext()) {
        Request next = it.next();
    
      out.write("\r\n");
      out.write("<p>\r\n");
      out.write("    <form action = \"/admin\" method = \"post\" name = \"action\">\r\n");
      out.write("        <label>Name: </label> ");
      out.print(next.getName() );
      out.write(" &nbsp\r\n");
      out.write("        <label>Surname: </label> ");
      out.print(next.getSurname() );
      out.write(" &nbsp\r\n");
      out.write("        <label>ID: </label> ");
      out.print(next.getID() );
      out.write(" &nbsp\r\n");
      out.write("        <input type=\"submit\" value=\"Accept\" name = ");
      out.print("accept"+next.getID());
      out.write(">\r\n");
      out.write("        <input type=\"submit\" value=\"Reject\" name = ");
      out.print("reject"+next.getID());
      out.write(">\r\n");
      out.write("        <input type = \"hidden\" name= \"hidden\" value = ");
      out.print(next.getID());
      out.write(" >\r\n");
      out.write("    </form>\r\n");
      out.write("</p>\r\n");
      out.write("\r\n");
      out.write("\r\n");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("<br>\r\n");
      out.write("<a href=\"/home\"> Back To Home </a>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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
