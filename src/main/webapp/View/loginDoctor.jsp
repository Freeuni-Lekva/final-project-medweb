<%@ page import="freeuni.edu.ge.DAO.InMemoryAdmnDao" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.DAO.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %><%--
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
        <label>Speciality: </label> <%=doctor.getSpeciality() %> </br>
        <label>Qualification: </label> <%=doctor.getQualification() %> </br>
        <label>Experience: </label> <%=doctor.getYearExperience() %> </br>
        <label>City: </label> <%=doctor.getCity() %> </br>
        <label>ID: </label> <%=doctor.getID() %> </br>
        <label>Mobile: </label> <%=doctor.getMobileNumber() %> </br>

        <form action="/loginDc" method="post">
                    <input type="submit" value = "Log Out" name = "logOut">
                    <input type="submit" value = "Edit Info" name = "edit">
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

</body>
</html>
