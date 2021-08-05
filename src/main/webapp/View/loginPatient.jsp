<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.InMemoryAdmnDao" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.DAO.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.Models.Visit" %>
<%@ page import="freeuni.edu.ge.Models.Administrator" %>
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

        <label>Date of birth: </label> <%=patient.getDateOfBirth() %> </br>

        <label>sex: </label> <%=patient.getSex() %> </br>

        <label>City: </label> <%=patient.getCity() %> </br>

        <label>ID: </label> <%=patient.getID() %> </br>

        <label>Address: </label> <%=patient.getAddress() %> </br>

        <label>Mobile: </label> <%=patient.getMobileNumber() %> </br>

        <form action="/loginPT" method="post">
            <input type="submit" value = "Update Personal Information" name = "update"><br>
            <input type="submit" value = "Log Out" name = "logOut">
        </form>
    </p>

    <% if(patient.hasVisits()) {%>

        <h2>Currently You have no visits booked!</h2>

    <%} else {%>

        <h2>Your Physical Visits:</h2>

        <ol>
            <% for(Visit visit : patient.getVisits()) { %>
                <li>Doctor: <%=visit.getDoctorName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %></li> </br>
            <%}%>
        </ol>

        <form action="/bookDC" method="post">
            <input type="submit" value = "Book New Visit" name = "book">
        </form>

    <%}%>

    <h2>Your Online Visits:</h2>

    <ol>
        <% AdministratorDao adminDao = (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
            Iterator<Visit> it = adminDao.getVisitsIterator("6000","Online");
            while(it.hasNext()) {
            Visit visit = it.next();
        %>
        <form action = "/chat" method = post>
            <li>Doctor: <%=visit.getDoctorName() %> <br> Reason: <%=visit.getReason() %> <br> Date: <%=visit.getDate() %>
                <input type = "hidden" name = "doctor" id = "doctor" value = <%=visit.getDoctorId()%> >
                <input type = "hidden" name = "patient" id = "patient" value = <%=visit.getPatientId()%> >
                <input type = "submit" value = "Open Chat">
            </li> </br>
        </form>
        <%}%>
    </ol>


    <%-- needs to be declired (form action url) --%>
    <form action="/bookDC" method="post">
        <input type="submit" value = "Book New Visit" name = "book">



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
