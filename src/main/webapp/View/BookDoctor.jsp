<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="javax.print.Doc" %>
<%@ page import="java.util.List" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.DoctorDAO" %>
<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.AdministratorDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.DoctorCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.Map" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.PatientCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.GeneralCommandsSQL" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/28/2021
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book New Visit</title>
    <link href="/static/BookDoctorCSS.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header">
    <h1>Book Your Visit</h1>
</div>

    <% if(request.getAttribute("doctors") == null) {%>
    <p>Select your option from the drop-down list.</p>
    <form action="/bookDC" method="post">
        <label for="specialty">Choose a department:</label>
        <select name="specialty" id="specialty" >
            <% for (Doctor.DoctorSpecialities specialty : Doctor.DoctorSpecialities.values()) {%>
                <option value=<%=specialty%>> <%=specialty%></option>
            <%}%>
        </select>

        <br><br>

        <label for="degree">Choose a degree:</label>
        <select name="degree" id="degree" >
            <% for (Doctor.Doctor_Qualifications degree : Doctor.Doctor_Qualifications.values()) {%>
            <option value=<%=degree%>> <%=degree%></option>
            <%}%>
        </select>
        <br><br>
        <input type="submit" value="Submit" name="filter" class="back">
        <input type = "hidden" name = "BookOnId" value = <%=request.getAttribute("BookOnId")%>>
        <p>Click the "Submit" button to filter doctors and get your options.</p>
    </form>
    <%} else {%>
    <form action="/bookDC" method="post">
    <p>Select doctor from the drop-down list.</p>

        <label>Choose a department: ${specialty}</label> <br><br>
        <label>Choose a degree: ${degree}</label> <br><br>

    <%
        List<Doctor> doctors = (ArrayList<Doctor>) request.getAttribute("doctors");
    %>

    <% if(!doctors.isEmpty()) {%>
        <br>

    <label for="doctorr">Choose a doctor:</label>
    <select name="doctorr" id="doctorr">
        <% for (int i=0; i<doctors.size(); i++) {%>
            <option value=<%=doctors.get(i).getID()%>> <%=doctors.get(i).getName()%></option>
        <%}%>
    </select>

        <input type="submit" value = "Choose Doctor" name = "choose" class="back"> <br>
    </form>

    <br><br><br><br>



    <%} else {%>
        <label>Doctors with current qualification and specialty does not exist.</label> <br><br>
    <%}%>
    <%}%>

<form action="/loginPT">
    <input type="submit" value="Back To Home" class="back">
</form>

</body>
</html>
