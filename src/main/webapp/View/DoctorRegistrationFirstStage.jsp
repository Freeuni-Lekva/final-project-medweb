<html>
<head>
    <title>Doctor Registration</title>
    <link href="/static/DoctorRegistrationFirstStageCSS.css" rel="stylesheet" type="text/css">
</head>

<body>

<h1>Doctor Registration</h1><br>

<%if(request.getAttribute("message")== null) {%>

<form action = "/dr" method ="post">
    <label>First Name</label>
    <input type = "text" name = "name"><br>
    <label>Surname</label>
    <input type = "text" name = "surname"><br>
    <label>ID Number</label>
    <input type = "text" name = "ID"><br>
    <input type="submit" name = "submit" class="doctorSubmit">
</form>

<%} else {%>
<h1>
<%=request.getAttribute("message")%>
</h1>
<%}%>
<br>
<form action="/home">
    <input type="submit" value="Back To Home" class="buttonDC">
</form>

</body>


</html>