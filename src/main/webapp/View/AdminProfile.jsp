<%@ page import="java.util.Iterator" %>
<%@ page import="freeuni.edu.ge.Models.Request" %>
<%@ page import="freeuni.edu.ge.DAO.InMemory.AdministratorDao" %>
<%@ page import="freeuni.edu.ge.DAO.Interfaces.AdministratorCommands" %>
<%@ page import="freeuni.edu.ge.DAO.SQLImplementation.AdminCommandsSQL" %>
<html>
<head>

<title>Admin</title>
</head>
<link rel="stylesheet" type="text/css" href="/static/Admin.css" />
<body>

<div id = "header">
    <br>
   <img src = "resources/ForDesign/Icons8-Windows-8-Users-Administrator-2.ico" id = "image">
    <br>This is Admin Panel, Please proceed with caution!

</div>

<div id = "sidebar">
</div>

<div id = "data">
<h4>See Doctor Registration Requests:</h4>

<%
    HttpSession adminSession = request.getSession();
    AdministratorCommands dao = (AdminCommandsSQL) adminSession.getAttribute("DAO");
    Iterator<Request> it = dao.getIterator();
    while(it.hasNext()) {
        Request next = it.next();
    %>
<p>
    <form action = "/admin" method = "post" name = "action">
        <label>Name: </label> <%=next.getName() %> &nbsp
        <label>Surname: </label> <%=next.getSurname() %> &nbsp
        <label>ID: </label> <%=next.getID() %> &nbsp
        <input type="submit" value="Accept" name = <%="accept"+next.getID()%>>
        <input type="submit" value="Reject" name = <%="reject"+next.getID()%>>
        <input type = "hidden" name= "hidden" value = <%=next.getID()%> >
    </form>
</p>


<%}%>

<br>
<a href="/home"> Back To Home </a>
</div>
</body>

</html>