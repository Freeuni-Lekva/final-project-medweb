<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.Models.Visit" %>
<%@ page import="freeuni.edu.ge.Models.Administrator" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="freeuni.edu.ge.DAO.*" %><%--
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
</head>
<body>

    <h2>Personal Information</h2>
    <p>


        

        <label>Name: </label> <%=patient.getName() %> <br>


        <label>Surname: </label> <%=patient.getSurname() %> <br>

        <label>Date of birth: </label> <%=patient.getDateOfBirth() %> <br>

        <label>sex: </label> <%=patient.getSex() %> <br>

        <label>City: </label> <%=patient.getCity() %> <br>

        <label>ID: </label> <%=patient.getID() %> <br>

        <label>Address: </label> <%=patient.getAddress() %> <br>

        <label>Mobile: </label> <%=patient.getMobileNumber() %> <br>

        <form action="/loginPT" method="post">
            <input type="submit" value = "Update Personal Information" name = "update"><br>
            <input type="submit" value = "Log Out" name = "logOut">
        </form>
    </p>

    <% if(!dao.hasVisits(id,"Online")&&!dao.hasVisits(id,"Physical")) {%>

        <h2>Currently You have no visits booked!</h2>

    <%} else {%>

        <h2>Your Physical Visits:</h2>

        <ol>
            <% Iterator<Visit> itP = dao.getPatientVisitsIterator(id,"Physical");
            while(itP.hasNext()){
            Visit visit = itP.next();%>
                <li>Doctor: <%=dao.getDoctorById(visit.getDoctorId()).getName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %></li> </br>
            <%}%>
        </ol>

    <%}%>

    <h2>Your Online Visits:</h2>

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
                <input type = "submit" value = "Open Chat">
            </li> </br>
        </form>
        <%}%>
    </ol>


        <form action="/bookDC" method="post">
            <input type="submit" value = "Book New Visit" name = "book">
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
