<%@ page import="freeuni.edu.ge.Patient" %>
<%@ page import="freeuni.edu.ge.InMemoryAdmnDao" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/24/2021
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    InMemoryAdmnDao dao = (InMemoryAdmnDao) request.getServletContext().getAttribute("AdministratorDAO");
    Patient patient = dao.getPatientById(id);
%>
<html>
<head>
    <title>Welcome <%=patient.getName()%>!</title>
</head>
<body>

    <h2>Personal Information</h2>
    <p>
        <label>Name: </label> <%=patient.getName() %> </br>
        <label>Surname: </label> <%=patient.getSurname() %> </br>
        <label>Date of birth: </label> <%=patient.getDateOfBirth().toString() %> </br>
        <label>sex: </label> <%=patient.getSex() %> </br>
        <label>City: </label> <%=patient.getCity() %> </br>
        <label>ID: </label> <%=patient.getID() %> </br>
        <label>Address: </label> <%=patient.getAddress() %> </br>
        <label>Mobile: </label> <%=patient.getMobileNumber() %> </br>
    </p>


</body>
</html>
