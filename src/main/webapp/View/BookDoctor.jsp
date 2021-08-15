<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="javax.print.Doc" %>
<%@ page import="java.util.List" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.DoctorDAO" %>
<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.AdministratorDao" %>
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

        <br><br>

        <table bgcolor="lightgray" border="5" width="60%" cellpadding="5" cellspacing="0.5" color="blue" >
            <tr>

                <th colspan ="3" bgcolor="#999999"><br>


                    <div align="Center" > <font face="verdana" size="5" color="white"> Choose Time </font>
                    </div>
                </th>
            </tr>

            <tr>
                <td>
                    <select name="doctor" >
                        <% for (Doctor doctor : doctors) {%>
                        <option value=<%=doctor.getID()%>> <%=doctor.getName()%> <%=doctor.getSurname()%></option>
                        <%}%>
                    </select>
                </td>
                <td>
                    <input type="radio" id="10:00 - 11:00" name="date" value="10:00 - 11:00">
                    <label for="10:00 - 11:00">10:00 - 11:00</label><br>

                    <input type="radio" id="11:00 - 12:00" name="date" value="11:00 - 12:00">
                    <label for="11:00 - 12:00">11:00 - 12:00</label><br>

                    <input type="radio" id="12:00 - 13:00" name="date" value="12:00 - 13:00">
                    <label for="12:00 - 13:00">12:00 - 13:00</label><br>

                    <input type="radio" id="13:00 - 14:00" name="date" value="13:00 - 14:00">
                    <label for="13:00 - 14:00">13:00 - 14:00</label><br>

                    <input type="radio" id="14:00 - 15:00" name="date" value="14:00 - 15:00">
                    <label for="14:00 - 15:00">14:00 - 15:00</label><br>

                    <input type="radio" id="15:00 - 16:00" name="date" value="15:00 - 16:00">
                    <label for="15:00 - 16:00">15:00 - 16:00</label><br>

                    <input type="radio" id="16:00 - 17:00" name="date" value="16:00 - 17:00">
                    <label for="16:00 - 17:00">16:00 - 17:00</label><br>

                    <input type="radio" id="17:00 - 18:00" name="date" value="17:00 - 18:00">
                    <label for="17:00 - 18:00">17:00 - 18:00</label><br>

                    <input type="radio" id="18:00 - 19:00" name="date" value="18:00 - 19:00">
                    <label for="18:00 - 19:00">18:00 - 19:00</label><br>

                    <input type="radio" id="19:00 - 20:00" name="date" value="19:00 - 20:00">
                    <label for="19:00 - 20:00">19:00 - 20:00</label><br>

            </tr>

        </table>

        <tr>  <tr>
        <br> <br>

        <p>Click the "Submit" button to choose a doctors.</p> <br><br>

        <input type="submit" value="Submit" name="choose">

    </form>

    <%} else {%>

        <label>Doctors with current qualification and specialty does not exist.</label> <br><br>
    <%}%>
    <%}%>

    <a href="/loginPT"> Back To Profile </a>

</body>
</html>
