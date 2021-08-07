<%@ page import="freeuni.edu.ge.DAO.InMemoryAdmnDao" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.DAO.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="freeuni.edu.ge.Models.Visit" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/24/2021
  Time: 2:21 PM
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
    <title>Welcome <%=doctor.getName()%>!</title>
</head>
<body>

    <h2>Personal Information</h2>
    <p>
        <label>Name: </label> <%=doctor.getName() %> </br>
        <label>Surname: </label> <%=doctor.getSurname() %> </br>
        <label>City: </label> <%=doctor.getCity() %> </br>
        <label>ID: </label> <%=doctor.getID() %> </br>
        <label>Mobile: </label> <%=doctor.getMobileNumber() %> </br>
    </p>

<%--
    <% if(!doctor.getVisits.isEmpty()) { %>
        <ul>
            <% List<Visits> visits = doctor.getVisits();
                for(Visit visit : visits) { %>
                    <li><%=visit.getPatientName()%> - doctor.getName()</li>
            <%}%>
        </ul>
    <%}%>
--%>

    <h2>Your Online Visits:</h2>

    <ol>
        <% AdministratorDao adminDao = (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
            Iterator<Visit> it = adminDao.getVisitsIterator(doctor.getID(),"Online");
            while(it.hasNext()) {
                Visit visit = it.next();
        %>
        <form action = "/chat?tp=d" method = post>
            <li>Doctor: <%=visit.getDoctorName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>
                <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                <input type = "submit" value = "Open Chat">
            </li> </br>
        </form>
        <%}%>
    </ol>



</body>
</html>
