<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.DoctorCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL" %><%--
  Created by IntelliJ IDEA.
  User: User

  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = (String) request.getAttribute("id");
    DoctorCommands dao = (DoctorCommandsSQL) request.getSession().getAttribute("DAO");
    Doctor doctor = dao.getDoctorById(id);
%><html>
<head>
    <title>Edit Info</title>

    <h1>Edit personal information!</h1>
</head>

<body>

    <form action = "/loginDc" method = "POST">
        <ul>

        <li>City: <input type="text" name="city" value = <%=doctor.getCity()%>></li>

        <li>ID: <input type="text" name="id" readonly value = <%=doctor.getID()%>></li>

        <li>Address: <input type="text" name="speciality" value = <%=doctor.getSpeciality()%>></li>

        <li>Mobile: <input type="text" name="mobile" value = <%=doctor.getMobileNumber()%>></li>

        <li>Qualification: <input type="text" name="qualification" value = <%=doctor.getQualification()%>></li>

        <li>Experience: <input type="text" name="yearExperience" value = <%=doctor.getYearExperience()%>></li>

        </ul>

        <input type="submit" value="Update Information" name="updateInformation">
    </form>

</body>
</html>