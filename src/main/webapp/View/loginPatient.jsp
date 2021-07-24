<%@ page import="freeuni.edu.ge.Patient" %>
<%@ page import="freeuni.edu.ge.InMemoryAdmnDao" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.AdministratorDao" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/24/2021
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = (String) request.getAttribute("id");
    //System.out.println(id);
    AdministratorDao dao = (AdministratorDao) request.getServletContext().getAttribute("AdministratorDAO");
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
    
    <%--
    <% if(!patient.getFamily().isEmpty()) { %>
        <ul>
            <% Map<String, String> family = patient.getFamily();
                for(String name : family.keySet()) { %>
                    <li><%=family.get(name)%> - name</li>
            <%}%>
        </ul>
    <%}%>
--%>
</body>
</html>
