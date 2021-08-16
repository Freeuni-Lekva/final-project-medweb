<html>
<head>
    <title>Doctor Registration</title>
    <link href="/static/DoctorRegistrationFirstStageCSS.css" rel="stylesheet" type="text/css">
</head>

<body>


<%if(request.getAttribute("message")== null) {%>

<div class="header">
<h1>Doctor Registration</h1>
</div>
<br><br>
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
    <div class="header">
        <%=request.getAttribute("message")%>
    </div>
</h1>
<%}%>
<br>
<form action="/home">
    <input type="submit" value="Back To Home" class="buttonDC">
</form>

</body>

</html>