<html>
<head>
    <title>Login</title>

</head>
<body>
 <h1> Login </h1>
    <br>
    <form action = "/home" method="post">
        <label>ID Number </label>
        <input type="text" name = "ID">
        <br>
        <label>Password</label>
        <input type="password" name = "pass">
        <br>
        <input type="submit" value = "LOGIN" name="submit">
    </form>

    <br>
    <a href="/pr"> Sign Up As Patient</a> &nbsp &nbsp
    <a href="/dr"> Sign Up As Doctor</a>

    <%if(request.getAttribute("message") != null){%>
    <%=request.getAttribute("message")%>
    <%}%>

</body>


</html>