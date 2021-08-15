<%@ page import="java.util.Date" %>
<%@ page import="javax.swing.*" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <h1>Registration Form</h1>
        <%if(request.getAttribute("Registered") != null) {%>
            <% if((Boolean)request.getAttribute("Registered")){%>
                <h2>This ID Number is used!</h2>
             <%}%>

        <%} %>

        <%if(request.getAttribute("NotFilled") != null  ) {%>
                <h2><%=request.getAttribute("NotFilled") %></h2>
        <%} %>

        <form action = "/pr" method="post">

            <div class="firstName">
            <label>First Name </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "fName" value = <%=request.getAttribute("fName")%> ><br>
            <%}else {%>
                <input type="text" name = "fName"><br>
            <%}%>
            </div>

            <div class="lastName">
            <label>Last Name </label>
            <% if(request.getAttribute("Optional") != null|| request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "lName" value = <%=request.getAttribute("lName")%> ><br>

            <%}else {%>
                <input type="text" name = "lName" ><br>
            <%}%>
            </div>

            <div class="idNumebr">
            <label>ID Number </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "ID" value = <%=request.getAttribute("ID")%> ><br>
            <%}else {%>
                <input type="text" name = "ID" ><br>
            <%}%>
            </div>

            <div class="password">
            <label>Password </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="password" name="password" value = <%=request.getAttribute("password")%>><br>
            <%}else {%>
                <input type="password" name = "password"><br>
            <%}%>
            </div>

            <div class="birthday">
            <label>Birthday </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="date" name="birthday" value = <%=request.getAttribute("birthday")%> ><br>
            <%}else {%>
                <input type="date" name = "birthday"><br>
            <%}%>
            </div>

            <div class="city">
            <label>City </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "city" value = <%=request.getAttribute("city")%> ><br>
            <%}else {%>
                <input type="text" name = "city"><br>
            <%}%>
            </div>

            <div class="sex">
            <label>Sex </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "sex" value = <%=request.getAttribute("sex")%> ><br>
            <%}else {%>
            <input type="text" name = "sex"><br>
            <%}%>
            </div>

            <div class="address">
            <label>Address </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "address" value = <%=request.getAttribute("address")%> ><br>
            <%}else {%>
                <input type="text" name = "address"><br>
            <%}%>
            </div>

            <div class="mobile">
            <label>Mobile Number </label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "mNumber" value = <%=request.getAttribute("mNumber")%>><br>
            <%}else {%>
                <input type="text" name = "mNumber"><br>
            <%}%>
            </div>

            <div class="additional">
            <label>Additional Information. (Diseases)</label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "diseases" value = <%=request.getAttribute("diseases")%> ><br>
            <%}else {%>
                <input type="text" name = "diseases"><br>
            <%}%>
            </div>

            <div class="additionalTwo">
            <label>Additional Information. (Allergies)</label>
            <% if(request.getAttribute("Optional") != null || request.getAttribute("NotFilled") != null) {%>
                <input type="text" name = "alergies" value = <%=request.getAttribute("alergies")%> ><br>
            <%}else {%>
                <input type="text" name ="alergies"><br>
            <%}%>
            </div>

            <label>Family Members(Optional).Click Add </label>
            <input type="submit" value = "Add" name = "add"> <br>


            <% if(request.getAttribute("Optional") != null) {%>
                <% if((Boolean)request.getAttribute("Optional")){%>
                    <label>Father ID Number</label>
                    <input type="text" name = "fID"><br>
                    <label>Mother ID Number</label>
                    <input type = "text" name = "mID"> <br>
                <%}%>
            <%}%>
            <br>
            <input type="submit" value = "Submit" name = "submit"><br><br>
            <a href="/home"> Back To Home </a>
        </form>
    </body>

<style>
    input[type=text], input[type=password], input[type=date] {
        width: 100%;
        padding: 10px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

</style>
</html>