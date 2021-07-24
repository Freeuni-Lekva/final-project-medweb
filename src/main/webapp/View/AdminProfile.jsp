<%@ page import="java.util.Iterator" %>
<%@ page import="freeuni.edu.ge.Request" %>
<%@ page import="freeuni.edu.ge.AdministratorDao" %>
<html>
<head>

<title>Admin</title>
</head>

<body>

<h4>See Doctor Registration Requests:</h4>

<%
    AdministratorDao dao = (AdministratorDao)request.getServletContext().getAttribute("AdministratorDAO");
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

</body>

</html>