<%@ page import="freeuni.edu.ge.Doctor" %>
<html>
<head>
<title>Registration</title>

</head>

<body>

<%
Doctor doc = (Doctor) request.getAttribute("doctor");
%>


<form action = "/dr" method = "post">
    <label for = "username">Username: </label>
    <input type = "text" name = "username" readonly id = "username" value = <%=doc.getName()%>>
    <br>
    <label for = "surname">Surname: </label>
    <input type = "text" name = "surname" readonly id = "surname" value = <%=doc.getSurname()%>>
    <br>
    <label for = "ID">ID: </label>
    <input type = "text" name = "ID" readonly id = "ID" value = <%=doc.getID()%>>
    <br>
    <label for = "pass">Password: </label>
    <input type = "password" name = "pass" id = "pass" required>
    <br>
    <label for = "speciality">Speciality: </label>
    <select name="speciality" id="speciality" required onfocus="this.size=5;" onblur="this.size = 1;" onchange="this.size=1; this.blur();">
        <option>Allergy_Immunology</option>
        <option>Anesthesiology</option>
        <option>Dermatology</option>
        <option>Diagnostic_Radiology</option>
        <option>Emergency_Medicine</option>
        <option>Family_Medicine</option>
        <option>Internal_Medicine</option>
        <option>Medical_Generics</option>
        <option>Neurology</option>
        <option>Nuclear_Medicine</option>
        <option>Obstetrics_Gynecology</option>
        <option>Ophthalmology</option>
        <option>Pathology</option>
        <option>Pediatrics</option>
        <option>Physical_Medicine_And_Rehabilitation</option>
        <option>Preventive_Medicine</option>
        <option>Psychiatry</option>
        <option>Radiation_Oncology</option>
        <option>Surgery</option>
        <option>Urology</option>
    </select>
    <br>
    <label for = "city">City: </label>
    <input type = "text" name = "city" id = "city">
    <br>
    <label for = "mobile">Mobile Number: </label>
    <input type = "text" name = "mobile" id = "mobile">



    <br>
    <input type = "submit" name = "register" value = "Register">



</form>

</body>

</html>