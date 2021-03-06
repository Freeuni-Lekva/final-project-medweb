<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.PatientCommands" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/25/2021
  Time: 6:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = (String) request.getAttribute("id");
    PatientCommands dao = (PatientCommandsSQL) request.getSession().getAttribute("DAO");
    Patient patient = dao.getPatientById(id);
%>
<html>
<head>
    <title>Update Information</title>
    <link href="/static/UpdatePersonalInformationPatientCSS.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="header">
    <h1>Update your personal information!</h1>
</div>

    <form action = "/loginPT" method = "POST">
        <ul>

        <li>Name: <input type="text" name="name" value = <%=patient.getName()%> readonly></li>

        <li>Surname: <input type="text" name="surname" value = <%=patient.getSurname()%> readonly></li>

        <li>Date of Birth: <input type="date" name="birthdate" value = <%=patient.getDateOfBirth()%>></li>

        <li>Sex: <input type="text" name="sex" value = <%=patient.getSex()%>></li>

        <li>City: <input type="text" name="city" value = <%=patient.getCity()%>></li>

        <li>ID: <input type="text" name="idNum" value = <%=patient.getID()%> readonly></li>

        <li>Address: <input type="text" name="address" value = <%=patient.getAddress()%>></li>

        <li>Mobile: <input type="text" name="mobile" value = <%=patient.getMobileNumber()%>></li>

        </ul>

        <input type="submit" value="Update Information" name="updateInformation" class="back">
    </form>

</body>
</html>
