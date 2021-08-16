<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.Models.Visit" %>
<%@ page import="freeuni.edu.ge.Models.Administrator" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="freeuni.edu.ge.DAO.*" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.PatientCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/24/2021
  Time: 2:21 PM
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
    <title>Welcome <%=patient.getName()%>!</title>
    <link href="/static/loginDoctorCSS.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header">
    <h2>Personal Information</h2>
</div>
    <p>

        <label>Name: <%=patient.getName() %></label>  <br>

        <label>Surname: <%=patient.getSurname() %></label> <br>

        <label>Date of birth: <%=patient.getDateOfBirth() %></label> <br>

        <label>sex: <%=patient.getSex() %></label> <br>

        <label>City: <%=patient.getCity() %></label> <br>

        <label>ID: <%=patient.getID() %></label> <br>

        <label>Address: <%=patient.getAddress() %></label> <br>

        <label>Mobile: <%=patient.getMobileNumber() %></label> <br>

        <form action="/loginPT" method="post">
            <input type="submit" value = "Update Personal Information" name = "update" class="back">
            <input type="submit" value = "Log Out" name = "logOut" class="back">
        </form>
    </p>

    <% if(!dao.hasVisits(id,"Online")&&!dao.hasVisits(id,"Physical")) {%>

<div class="header">
        <h2>Currently You have no visits booked!</h2>
</div>

    <%} else {%>

<div class="header">
        <h2>Your Physical Visits:</h2>
</div>

        <ol>

            <% Iterator<Visit> itP = dao.getPatientVisitsIterator(id,"Physical");
            while(itP.hasNext()){
            Visit visit2 = itP.next();%>
                <li>Doctor: <%=dao.getDoctorById(visit2.getDoctorId()).getName() %> <br> Reason: <%=visit2.getReason() %> <br> Date: <%=visit2.getDate() %></li> </br>

            <%}%>
        </ol>



<div class="header">
    <h2>Your Online Visits:</h2>
</div>

    <ol>
        <%
            Iterator<Visit> itO = dao.getPatientVisitsIterator(patient.getID(),"Online");
            while(itO.hasNext()) {
            Visit visit = itO.next();
        %>
        <form action = "/chat?tp=p" method = post>
            <li>Doctor: <%=dao.getDoctorById(visit.getDoctorId()).getName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>

                <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                <input type = "submit" value = "Open Chat" class="back">
            </li> </br>
        </form>
        <%}%>
        <%}%>
    </ol>



        <form action="/bookDC" method="post">
            <input type="submit" value = "Book New Visit" name = "book" class="back">
            <input type="hidden" name = "BookOnId" value = <%=patient.getID()%>>
        </form>

<div class="header">
    <h3>See Your History Of Visits</h3>
</div>
    <form action="/ptHistory" method="get">
        <input type="submit" value = "Your History" name = "history" class="back">
        <input type="hidden" name = "hiddenID" value=<%=patient.getID()%>>
    </form>

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
