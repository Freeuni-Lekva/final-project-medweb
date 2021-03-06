/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-16 08:14:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import freeuni.edu.ge.DAO.Interfaces.PatientCommands;
import java.util.Map;

public final class WorkingTimes_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Working Times</title>\r\n");
      out.write("    <link href=\"/static/BookDoctorCSS.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<form action=\"/bookDC\" method=\"post\" style = \"display: inline\">\r\n");
      out.write("<label>Reason</label>\r\n");
      out.write("<input type = \"text\" name = \"reason\"> <br><br>\r\n");

    String doctorID = (String) request.getAttribute("ID");
    PatientCommands dao = (PatientCommandsSQL) request.getSession().getAttribute("DAO");
    Map<String, Map<Date, List<Time>>> result = dao.getAllDoctorWorkingTime();
    String abc = (String) request.getAttribute("ID");
    String patientID= (String) request.getAttribute("BookOnId");
    Map<Date, List<Time>> doctorTimes = result.get(abc);

      out.write("\r\n");
      out.write("\r\n");
      out.write("    <select name = \"typeSelect\">\r\n");
      out.write("        <option value = \"online\">Online</option>\r\n");
      out.write("        <option value = \"physical\">Physical</option>\r\n");
      out.write("    </select>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("<div class = \"times\" style=\"overflow-x: scroll; height: 200px; width:1000px; overflow-y: hidden; background: transparent;\">\r\n");
      out.write("    ");

        for(Date date : doctorTimes.keySet()){
            List<Time> list = doctorTimes.get(date);
            String val = "" + date.getYear() + "-" + date.getMonth() + "-" +date.getDate();
    
      out.write("\r\n");
      out.write("    <div style = \"display: inline\">\r\n");
      out.write("        <label>");
      out.print(val);
      out.write("</label>\r\n");
      out.write("        ");

            for(Time time : list){
        
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <input type = \"submit\" name = \"timeButton\" class=\"back\" value = ");
      out.print(time.toString());
      out.write(">\r\n");
      out.write("            <input type = \"hidden\" name = \"DoctorID\" value=");
      out.print(doctorID);
      out.write(">\r\n");
      out.write("            <input type = \"hidden\" name = \"time\" value = ");
      out.print(time.toString());
      out.write(">\r\n");
      out.write("            <input type = \"hidden\" name = \"date\" value = ");
      out.print(val);
      out.write(">\r\n");
      out.write("            <input type = \"hidden\" name = \"PatientID\" value = ");
      out.print(patientID);
      out.write(">\r\n");
      out.write("\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    <br>\r\n");
      out.write("    ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<a href=\"/loginPT\"> Back To Profile </a>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
