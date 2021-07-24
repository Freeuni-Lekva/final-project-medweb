<html>
<head>
    <title>Doctor Registration</title>
</head>

<body>

<%if(request.getAttribute("message")== null) {%>

<form action = "/dr" method ="post">
    <label>First Name</label>
    <input type = "text" name = "name">
    <label>Surname</label>
    <input type = "text" name = "surname">
    <label>ID Number</label>
    <input type = "text" name = "ID">
    <input type="submit" name = "submit">
</form>

<%} else {%>
<h1>
<%=request.getAttribute("message")%>
</h1>
<%}%>
<br>
<a href="/home"> Back To Home </a>

</body>


</html>