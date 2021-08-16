<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 8/15/2021
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String doctorId = (String) request.getAttribute("doctorId");
    String patientId = (String) request.getAttribute("patientId");
%>
<html>
<head>
    <title>Conclusion</title>
    <link href="/static/Conclusion.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="header">
<h1>Please add your conclusion!</h1>
</div>

<form action="loginDc" method="post">
    <textarea id="conclusion" name="conclusion" rows="4" cols="50"></textarea>
    <input type = "hidden" name = "doctor" id = "doctor" value = <%=doctorId%> >
    <input type = "hidden" name = "patient" id = "patient" value = <%=patientId%> >
    <br><br>
    <input type="submit" name="submitConclusion" value="Submit" class="buttonDC">
</form>

</body>
</html>
