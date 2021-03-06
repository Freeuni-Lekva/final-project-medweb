<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.PatientCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.PatientCommandsSQL" %>
<%@ page import="freeuni.edu.ge.Models.History" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    String id = (String) request.getAttribute("id");
    PatientCommands dao = (PatientCommandsSQL) request.getSession().getAttribute("DAO");
    Patient patient = dao.getPatientById(id);
    Iterator<History> history = dao.getPatientHistory(id);
    boolean isHistory = true;
    if(history == null) isHistory = false;

%>

<html>
<head>
    <title><%=patient.getName()%> History</title>
    <link href="/static/HistoryCSS.css" rel="stylesheet" type="text/css">

    <h1>Your history of visits</h1>
</head>

<body>

<%if(isHistory){%>
<form action="/ptHistory" method="get">

    <% while(history.hasNext()) {%>


    <%History cur = history.next();%>


    <label>Doctor Name: </label> <%=dao.getDoctorById(cur.getDoctorId()).getName() %> <br>
    <label>Reason: </label> <%=cur.getReason() %> <br>
    <label>Medical Conclusion: </label> <%=cur.getMedicalConclusion()%> <br>
    <label> Visit Date: </label> <%=cur.getDate() %> <br>

    <br> <br>

    <%}%>
</form>

<%}%>

    <form action = "/loginPT" method = "post">
        <input type = "submit" value="Back To Profile" name = "backToProfile">
        <input type = "hidden" name = "id" value=<%=id%>>
    </form>

</body>
</html>



