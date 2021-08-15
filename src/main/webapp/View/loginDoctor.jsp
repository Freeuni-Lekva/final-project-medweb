<%@ page import="freeuni.edu.ge.DAO.InMemory.InMemoryAdmnDao" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="freeuni.edu.ge.Models.Visit" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.DoctorCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/24/2021
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = (String) request.getAttribute("id");
    DoctorCommands dao = (DoctorCommandsSQL) request.getSession().getAttribute("DAO");
    Doctor doctor = dao.getDoctorById(id);
%>
<html>
<head>
    <title>Welcome <%=doctor.getName()%>!</title>
    <link rel="stylesheet" href="../static/Doctor.css">
</head>
<body>

    <h2>Personal Information</h2>
    <p>
        <label>Name: </label> <%=doctor.getName() %> <br>
        <label>Surname: </label> <%=doctor.getSurname() %> <br>
        <label>Speciality: </label> <%=doctor.getSpeciality() %> <br>
        <label>Qualification: </label> <%=doctor.getQualification() %> <br>
        <label>Experience: </label> <%=doctor.getYearExperience() %> <br>
        <label>City: </label> <%=doctor.getCity() %> <br>
        <label>ID: </label> <%=doctor.getID() %> <br>
        <label>Mobile: </label> <%=doctor.getMobileNumber() %> <br>

        <form action="/loginDc" method="post">
                    <input type="submit" value = "Log Out" name = "logOut">
                    <input type="submit" value = "Edit Info" name = "edit">
                    <input type = "hidden" name = "id" value = <%=doctor.getID()%>>
                </form>
    </p>

<%--
    <% if(!doctor.getVisits.isEmpty()) { %>
    <h2>Visits:</h2>
        <ul>
            <% List<Visit> visits = doctor.getVisits();
                for(Visit visit : visits) { %>
                <form action = "/redirect" method = "post" >
                    <li>Doctor: <%=visit.getDoctorName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>
                        <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                        <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                        <input type = "submit" value = "Finish" name = "finish">
                        <input type = "submit" value = "Redirect" name = "redirect">
                    </li> </br>
                </form>
            <%}%>
        </ul>
    <%}%>
--%>

    <h2>Your Physical Visits:</h2>

    <ol>
            <%
            Iterator<Visit> itP = dao.getDoctorVisitsIterator(doctor.getID(),"Physical");
            while(itP.hasNext()) {
                Visit visit = itP.next();
        %>
                <li>Patient: <%=dao.getPatientById(visit.getPatientId()).getName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>

    <%}%>

    <h2>Your Online Visits:</h2>

    <ol>
        <%
            Iterator<Visit> it = dao.getDoctorVisitsIterator(doctor.getID(),"Online");
            while(it.hasNext()) {
                Visit visit = it.next();
        %>
        <form action = "/chat?tp=d" method = post>
            <li>Patient: <%=dao.getPatientById(visit.getPatientId()).getName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>
                <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                <input type = "submit" value = "Open Chat">
            </li> </br>
        </form>
        <%}%>
    </ol>



</body>
</html>
