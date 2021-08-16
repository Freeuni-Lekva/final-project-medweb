<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.PatientCommands" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.08.2021
  Time: 06:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/bookDC" method="post" style = "display: inline">
<label>Reason</label>
<input type = "text" name = "reason"> <br><br>
<%
    String doctorID = (String) request.getAttribute("ID");
    PatientCommands dao = (PatientCommandsSQL) request.getSession().getAttribute("DAO");
    Map<String, Map<Date, List<Time>>> result = dao.getAllDoctorWorkingTime();
    String abc = (String) request.getAttribute("ID");
    String patientID= (String) request.getAttribute("BookOnId");
    Map<Date, List<Time>> doctorTimes = result.get(abc);
%>

    <select name = "typeSelect">
        <option value = "online">Online</option>
        <option value = "physical">Physical</option>
    </select>
    <br>

<div class = "times" style="overflow-x: scroll; height: 200px; width:1000px; overflow-y: hidden; background: transparent;">
    <%
        for(Date date : doctorTimes.keySet()){
            List<Time> list = doctorTimes.get(date);
            String val = "" + date.getYear() + "-" + date.getMonth() + "-" +date.getDate();
    %>
    <div style = "display: inline">
        <label><%=val%></label>
        <%
            for(Time time : list){
        %>


            <input type = "submit" name = "timeButton" value = <%=time.toString()%>>
            <input type = "hidden" name = "DoctorID" value=<%=doctorID%>>
            <input type = "hidden" name = "time" value = <%=time.toString()%>>
            <input type = "hidden" name = "date" value = <%=val%>>
            <input type = "hidden" name = "PatientID" value = <%=patientID%>>

        <%}%>
        </form>
    </div>
    <br>
    <%}%>

</div>


<a href="/loginPT"> Back To Profile </a>




</body>
</html>
