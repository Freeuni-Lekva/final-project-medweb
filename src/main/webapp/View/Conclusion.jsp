<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/15/2021
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String doctorId = (String) request.getAttribute("doctorID");
    String patientId = (String) request.getAttribute("patientID");
%>
<html>
<head>
    <title>Conclusion</title>
</head>
<body>
<h1>Please add your conclusion!</h1>

<form action="loginDc" method="post">
    <input type = "text" name = "doctor" readonly value = <%=doctorId%> ><br>
    <input type = "text" name = "patient" readonly value = <%=patientId%> ><br>
    <input type = "text" name = "reason" readonly value= <%=(String) request.getAttribute("reason")%>><br>
    <input type = "text" name = "VisitType" readonly value=<%=(String) request.getAttribute("VisitType")%>><br>
    <input type="text" name = "VisitDate" readonly value=<%=(String) request.getParameter("VisitDate")%>><br>
    <textarea id="conclusion" name="conclusion" rows="4" cols="50"></textarea><br>
    <input type="submit" name="submitConclusion" value="Submit">
</form>

<form action = "http://localhost:8080/loginDc" method="get">
    <input type = "hidden" name = "id" value=<%=doctorId%>>
    <input type = "submit" value="Return To Profile">
</form>

</body>
</html>
