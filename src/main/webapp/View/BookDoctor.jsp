<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="javax.print.Doc" %>
<%@ page import="java.util.List" %>
<%@ page import="freeuni.edu.ge.DAO.DoctorDAO" %>
<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.AdministratorDao" %>
<%@ page import="java.util.ArrayList" %><%--
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
</head>
<body>

    <h1>Book Your Visit</h1>

    <% if(request.getAttribute("doctors") == null) {%>

    <p>Select your option from the drop-down list.</p>
    <form action="/bookDC" method="post">
        <label for="specialty">Choose a department:</label>
        <select name="specialty" id="specialty">
            <% for (Doctor.DoctorSpecialities specialty : Doctor.DoctorSpecialities.values()) {%>
                <option value=<%=specialty%>> <%=specialty%></option>
            <%}%>
        </select>

        <br><br>

        <label for="degree">Choose a degree:</label>
        <select name="degree" id="degree">
            <% for (Doctor.Doctor_Qualifications degree : Doctor.Doctor_Qualifications.values()) {%>
            <option value=<%=degree%>> <%=degree%></option>
            <%}%>
        </select>
        <br><br>
        <input type="submit" value="Submit" name="filter">
        <p>Click the "Submit" button to filter doctors and get your options.</p>
    </form>

    <%} else {%>

    <p>Select doctor from the drop-down list.</p>

        <label>Choose a department: ${specialty}</label> <br><br>
        <label>Choose a degree: ${degree}</label> <br><br>

    <%
        List<Doctor> doctors = (ArrayList<Doctor>) request.getAttribute("doctors");
    %>

    <% if(!doctors.isEmpty()) {%>
    <form action="/bookDC" method="post">
        <label for="doctor">Choose a doctor:</label>
        <select name="doctor" id="doctor">
            <% for (Doctor doctor : doctors) {%>
                <option value=<%=doctor.getID()%>> <%=doctor.getName()%> <%=doctor.getSurname()%></option>
            <%}%>
        </select>
        <br><br>
        <input type="submit" value="Submit" name="choose">
        <p>Click the "Submit" button to choose a doctors.</p> <br><br>

    </form>

    <%} else {%>

        <label>Doctors with current qualification and specialty does not exist.</label> <br><br>
    <%}%>
    <%}%>

    <a href="/loginPT"> Back To Profile </a>

</body>
</html>
