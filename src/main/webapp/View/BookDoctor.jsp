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
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %><%--
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
        <input type = "hidden" name = "BookOnId" value = <%=request.getAttribute("BookOnId")%>>
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
        <br>
    <select name = "typeSelect">
        <option value = "online">Online</option>
        <option value = "physical">Physical</option>
    </select>
        <br>

    <%
        String doctorID = (String) request.getAttribute("BookOnId");
        PatientCommands dao = (PatientCommandsSQL) request.getSession().getAttribute("DAO");
        Map<String, Map<Date, List<Time>>> result = dao.getAllDoctorWorkingTime();
        String abc = "6000";
        Map<Date, List<Time>> doctorTimes = result.get(abc);
    %>


    <div class = "times" style="overflow-x: scroll; height: 200px; width:1000px; overflow-y: hidden; background: transparent;">
        <%
            for(Date date : doctorTimes.keySet()){
                List<Time> list = doctorTimes.get(date);
                String s = date.toString().substring(0,11);
                String val = s.substring(4,10);
                val = val.replace(" ","/");
                %>
        <div style = "display: inline">
        <label><%=s%></label>
        <%
            for(Time time : list){
                %>

            <form action="/bookDC" method="post" style = "display: inline">
                <input type = "submit" name = "timeButton" value = <%=time.toString()%>>
                <input type = "hidden" name = "DoctorID" value=<%=doctorID%>>
                <input type = "hidden" name = "time" value = <%=time.toString()%>>
                <input type = "hidden" name = "date" value = <%=val%>>
            </form>
        <%}%>
        </div>
        <br>
        <%}%>


    </div>

    <%} else {%>
        <label>Doctors with current qualification and specialty does not exist.</label> <br><br>
    <%}%>
    <%}%>

    <a href="/loginPT"> Back To Profile </a>

</body>
</html>
