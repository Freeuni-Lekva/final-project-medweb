<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="freeuni.edu.ge.DAO.AdministratorDao" %><%--
  Created by IntelliJ IDEA.
  User: User

  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = (String) request.getAttribute("id");
    AdministratorDao dao = (AdministratorDao) request.getServletContext().getAttribute("AdministratorDAO");
    Doctor doctor = dao.getDoctorById(id);
%>
<html>
<head>
    <title>Edit Info</title>

    <h1>Edit personal information!</h1>
</head>
<body>

    <form action = "/loginDc" method = "POST">
        <ul>


        <li>City: <input type="text" name="city" value = <%=doctor.getCity()%>></li>

        <li>ID: <input type="text" name="id" value = <%=doctor.getID()%>></li>

        <li>Address: <input type="text" name="speciality" value = <%=doctor.getSpeciality()%>></li>

        <li>Mobile: <input type="text" name="mobile" value = <%=doctor.getMobileNumber()%>></li>

        <li>Mobile: <input type="text" name="qualification" value = <%=doctor.getQualification()%>></li>

        <li>Mobile: <input type="text" name="mobile" value = <%=doctor.getYearExperience()%>></li>

        </ul>

        <input type="submit" value="Update Information" name="updateInformation">
    </form>

</body>
</html>