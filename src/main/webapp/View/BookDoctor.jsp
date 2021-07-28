<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<%@ page import="javax.print.Doc" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 7/28/2021
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book New Visit</title>
</head>
<body>

    <h1>Book Your Visit</h1>

    <p>Select doctor from the drop-down list.</p>
    <form action="/bookDC" method="post">
        <label for="specialty">Choose a department:</label>
        <select name="specialty" id="specialty">
            <% for (Doctor.DoctorSpecialities specialty : Doctor.DoctorSpecialities.values()) {%>
                <option value=<%=specialty%>> <%=specialty%></option>
            <%}%>
        </select>
        <br><br>
        <label for="degree">Choose a degree:</label>
        <select name="degree" id="degree">
            <% for (Doctor.Doctor_Qualifications degree : Doctor.Doctor_Qualifications.values()) {%>
            <option value=<%=degree%>> <%=degree%></option>
            <%}%>
        </select>
        <br><br>
        <input type="submit" value="Get Doctors" name="submit">
    </form>

</body>
</html>
