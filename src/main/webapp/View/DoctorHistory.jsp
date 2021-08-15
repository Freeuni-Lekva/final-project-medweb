<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.PatientCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %>
<%@ page import="freeuni.edu.ge.Models.History" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.DoctorCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.DoctorCommandsSQL" %>
<%
    String id = (String) request.getAttribute("id");
    DoctorCommands dao = (DoctorCommandsSQL) request.getSession().getAttribute("DAO");
    Doctor doctor = dao.getDoctorById(id);
    Iterator<History> history = dao.getDoctorHistory(id);
    System.out.println(id+" eqimi");

%>

<html>
<head>
    <title><%=doctor.getName()%> History</title>

    <h1>Your history of visits</h1>
</head>

<body>

<form action="/drHistory" method="get">
    <% while(history.hasNext()) {%>

    <%History cur = history.next();%>

    <label>Patient Name: </label> <%=dao.getPatientById(cur.getPatientId()).getName() %> <br>
    <label>Reason: </label> <%=cur.getReason() %> <br>
    <label>Medical Conclusion: </label> <%=cur.getMedicalConclusion()%> <br>
    <label>Visit Date: </label> <%=cur.getDate() %> <br>

    <br> <br>

    <%}%>
</form>


<form action="/loginDc" method="get">
    <input type="submit" value=" Back To Your Profile Page" >
    <input type="hidden" name="id" value=<%=doctor.getID()%>>
</form>





</body>
</html>



