
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <form action = "/pr" method="post">
            <label>First Name </label>
            <input type="text" name = "fName"><br>

            <label>Last Name </label>
            <input type="text" name = "lName"><br>

            <label>ID Number </label>
            <input type="text" name = "ID"><br>

            <label>Password </label>
            <input type="text" name="password"><br>

            <label>Birthday </label>
            <input type="date" name="birthday"><br>

            <label>City </label>
            <input type="text" name = "city"><br>

            <label>Sex </label>
            <input type="text" name = "sex"><br>

            <label>Address </label>
            <input type="text" name = "address"><br>

            <label>Mobile Number </label>
            <input type="text" name = "mNumber"><br>

            <label>Additional Information. (Diseases)</label>
            <input type="text" name = "diseases"><br>

            <label>Additional Information. (Allergies)</label>
            <input type="text" name = "alergies"><br>

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
            <input type="submit" value = "Submit">

        </form>
    </body>
</html>