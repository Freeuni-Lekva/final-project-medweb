<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<html>
<head>
    <title>Registration</title>
    <link href="/static/DoctorRegistrationSecondStageCSS.css" rel="stylesheet" type="text/css">

</head>

<body>


<% if(request.getAttribute("message") == null) { %>


<%
Doctor doc = (Doctor) request.getAttribute("doctor");
%>

<div class="header">
<h1>Doctor Registration</h1>
</div>

<br><br>

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
        <option value = <%=Doctor.DoctorSpecialities.Allergy_Immunology%>>Allergy_Immunology</option>
        <option value = <%=Doctor.DoctorSpecialities.Anesthesiology%>>Anesthesiology</option>
        <option value = <%=Doctor.DoctorSpecialities.Dermatology%>>Dermatology</option>
        <option value = <%=Doctor.DoctorSpecialities.Diagnostic_Radiology%>>Diagnostic_Radiology</option>
        <option value = <%=Doctor.DoctorSpecialities.Emergency_Medicine%>>Emergency_Medicine</option>
        <option value = <%=Doctor.DoctorSpecialities.Family_Medicine%>>Family_Medicine</option>
        <option value = <%=Doctor.DoctorSpecialities.Internal_Medicine%>>Internal_Medicine</option>
        <option value = <%=Doctor.DoctorSpecialities.Medical_Generics%>>Medical_Generics</option>
        <option value = <%=Doctor.DoctorSpecialities.Neurology%>>Neurology</option>
        <option value = <%=Doctor.DoctorSpecialities.Nuclear_Medicine%>>Nuclear_Medicine</option>
        <option value = <%=Doctor.DoctorSpecialities.Obstetrics_Gynecology%>>Obstetrics_Gynecology</option>
        <option value = <%=Doctor.DoctorSpecialities.Ophthalmology%>>Ophthalmology</option>
        <option value = <%=Doctor.DoctorSpecialities.Pathology%>>Pathology</option>
        <option value = <%=Doctor.DoctorSpecialities.Pediatrics%>>Pediatrics</option>
        <option value = <%=Doctor.DoctorSpecialities.Physical_Medicine_And_Rehabilitation%>>Physical_Medicine_And_Rehabilitation</option>
        <option value = <%=Doctor.DoctorSpecialities.Preventive_Medicine%>>Preventive_Medicine</option>
        <option value = <%=Doctor.DoctorSpecialities.Psychiatry%>>Psychiatry</option>
        <option value = <%=Doctor.DoctorSpecialities.Radiation_Oncology%>>Radiation_Oncology</option>
        <option value = <%=Doctor.DoctorSpecialities.Surgery%>>Surgery</option>
        <option value = <%=Doctor.DoctorSpecialities.Urology%>>Urology</option>
    </select>
    <br>
    <label for = "city">City: </label>
    <input type = "text" name = "city" id = "city">
    <br>
    <label for = "mobile">Mobile Number: </label>
    <input type = "text" name = "mobile" id = "mobile">
    <br>
    <label for = "qualification">Qualification: </label>
    <select name="qualification" id="qualification" onfocus="this.size=3;" onblur="this.size = 1;" onchange="this.size=1; this.blur();">
        <option value = <%=Doctor.Doctor_Qualifications.Medical_School %>>Medical_School</option>
        <option value = <%=Doctor.Doctor_Qualifications.Bachelor_Of_Medicine %>>Bachelor_Of_Medicine</option>
        <option value = <%=Doctor.Doctor_Qualifications.Master_Of_Medicine %>>Master_Of_Medicine</option>
        <option value = <%=Doctor.Doctor_Qualifications.Doctor_Of_Medicine %>>Doctor_Of_Medicine</option>
    </select>
    <br>
    <label for = "experience">Year of Experience: </label>
    <input type = "text" name = "experience" id = "experience">
    <br>
    <label for = "graduation">Year of Graduation: </label>
    <input type = "text" name = "graduation" id = "graduation">

    <br>
    <input type = "submit" name = "register" value = "Register" class="submitDC">

<%} else {%>

    <div class="header">
        <h1><%=request.getAttribute("message")%></h1>
    </div>

    <br>
    <form action="/home">
        <input type="submit" value="Back To Home" class="buttonDC">
    </form>

    <%}%>


</form>

</body>

</html>