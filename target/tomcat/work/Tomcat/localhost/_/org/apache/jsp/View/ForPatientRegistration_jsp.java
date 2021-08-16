/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-16 06:34:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import javax.swing.*;

public final class ForPatientRegistration_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Registration</title>\r\n");
      out.write("          <link href=\"/static/ForPatientRegistrationCSS.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h1>Registration Form</h1>\r\n");
      out.write("        ");
if(request.getAttribute("Registered") != null) {
      out.write("\r\n");
      out.write("            ");
 if((Boolean)request.getAttribute("Registered")){
      out.write("\r\n");
      out.write("                <h2>This ID Number is used!</h2>\r\n");
      out.write("             ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("        ");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        ");
if(request.getAttribute("NotFilled") != null  ) {
      out.write("\r\n");
      out.write("                <h2>");
      out.print(request.getAttribute("NotFilled") );
      out.write("</h2>\r\n");
      out.write("        ");
} 
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <form action = \"/pr\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("            <div class=\"required\">\r\n");
      out.write("            <label>First Name </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"fName\" value = ");
      out.print(request.getAttribute("fName"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"fName\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"required\">\r\n");
      out.write("            <label>Last Name </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null|| request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"lName\" value = ");
      out.print(request.getAttribute("lName"));
      out.write(" ><br>\r\n");
      out.write("\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"lName\" ><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"required\">\r\n");
      out.write("            <label>ID Number </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"ID\" value = ");
      out.print(request.getAttribute("ID"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"ID\" ><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"required\">\r\n");
      out.write("            <label>Password </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"password\" name=\"password\" value = ");
      out.print(request.getAttribute("password"));
      out.write("><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"password\" name = \"password\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <label>Birthday </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"date\" name=\"birthday\" value = ");
      out.print(request.getAttribute("birthday"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"date\" name = \"birthday\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>City </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"city\" value = ");
      out.print(request.getAttribute("city"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"city\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>Sex </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"sex\" value = ");
      out.print(request.getAttribute("sex"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("            <input type=\"text\" name = \"sex\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>Address </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"address\" value = ");
      out.print(request.getAttribute("address"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"address\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>Mobile Number </label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"mNumber\" value = ");
      out.print(request.getAttribute("mNumber"));
      out.write("><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"mNumber\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>Additional Information. (Diseases)</label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"diseases\" value = ");
      out.print(request.getAttribute("diseases"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"diseases\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>Additional Information. (Allergies)</label>\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {
      out.write("\r\n");
      out.write("                <input type=\"text\" name = \"alergies\" value = ");
      out.print(request.getAttribute("alergies"));
      out.write(" ><br>\r\n");
      out.write("            ");
}else {
      out.write("\r\n");
      out.write("                <input type=\"text\" name =\"alergies\"><br>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("\r\n");
      out.write("            <label>Family Members(Optional).Click Add </label>\r\n");
      out.write("            <input type=\"submit\" value = \"Add\" name = \"add\" class=\"add\"> <br>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            ");
 if(request.getAttribute("Optional") != null) {
      out.write("\r\n");
      out.write("                ");
 if((Boolean)request.getAttribute("Optional")){
      out.write("\r\n");
      out.write("                    <label>Father ID Number</label>\r\n");
      out.write("                    <input type=\"text\" name = \"fID\"><br>\r\n");
      out.write("                    <label>Mother ID Number</label>\r\n");
      out.write("                    <input type = \"text\" name = \"mID\"> <br>\r\n");
      out.write("                ");
}
      out.write("\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            <br>\r\n");
      out.write("            <input type=\"submit\" value = \"Submit\" name = \"submit\" class=\"registerButton\"><br><br>\r\n");
      out.write("            <a href=\"/home\" class=\"back\"> Back To Home </a>\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
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
