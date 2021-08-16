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
    <link href="/static/loginDoctorCSS.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header">
    <h2>Personal Information</h2>
</div>

    <p>
        <label>Name: <%=doctor.getName() %> </label> <br>
        <label>Surname: <%=doctor.getSurname() %> </label> <br>
        <label>Speciality: <%=doctor.getSpeciality() %> </label> <br>
        <label>Qualification: <%=doctor.getQualification() %> </label> <br>
        <label>Experience: <%=doctor.getYearExperience() %> </label> <br>
        <label>City: <%=doctor.getCity() %> </label> <br>
        <label>ID: <%=doctor.getID() %> </label> <br>
        <label>Mobile: <%=doctor.getMobileNumber() %> </label> <br>

        <form action="/loginDc" method="post">
            <input type="submit" value = "Log Out" name = "logOut" class="back">
            <input type="submit" value = "Edit Info" name = "edit" class="back">
            <input type = "hidden" name = "id" value = <%=doctor.getID()%>>
        </form>
    </p>

<%--
    <% if(!doctor.getVisits.isEmpty()) { %>
    <h2>Visits:</h2>
        <ul>
            <% List<Visit> visits = doctor.getVisits();
                for(Visit visit : visits) { %>
                <form action = "/loginDc" method = "post" >
                    <li>Doctor: <%=visit.getDoctorName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>
                        <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                        <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                        <input type = "submit" value = "Finish" name = "finish">
                    </li> </br>
                </form>
            <%}%>
        </ul>
    <%}%>
--%>

<div class="header">
    <h2>Your Physical Visits:</h2>
</div>

    <ol>
            <%
            Iterator<Visit> itP = dao.getDoctorVisitsIterator(doctor.getID(),"Physical");
            while(itP.hasNext()) {
                Visit visit = itP.next();
        %>
        <li>Patient: <%=dao.getPatientById(visit.getPatientId()).getName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %></li>
        <form action="/loginDc" method="post">
            <input type = "submit" name="history" value="Clear Visit" class="back">
            <input type = "hidden" name = "doctor2" id = "doctor3" value = <%=visit.getDoctorId()%> >
            <input type = "hidden" name = "patient2" id = "patient3" value = <%=visit.getPatientId()%> >
        </form>

    <%}%>
                </ol>

     <div class="header">
    <h2>Your Online Visits:</h2>
     </div>

    <ol>
        <%
            Iterator<Visit> it = dao.getDoctorVisitsIterator(doctor.getID(),"Online");
            while(it.hasNext()) {
                Visit visit = it.next();
        %>
        <form action = "/chat?tp=d" method = post>
            <li>Patient: <%=dao.getPatientById(visit.getPatientId()).getName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %> <br>
                <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                <input type = "submit" value = "Open Chat" class="back">
            </li>
        </form>

        <form action="/loginDc" method="post">
            <input type = "submit" name="history" value="Clear Visit" class="back">
            <input type = "hidden" name = "doctor2" id = "doctor2" value = <%=visit.getDoctorId()%> >
            <input type = "hidden" name = "patient2" id = "patient2" value = <%=visit.getPatientId()%> >
        </form>
        <%}%>
    </ol>


</body>
</html>
