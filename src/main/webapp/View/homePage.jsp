<html>
<head>
    <title>Login</title>
    <link href="/static/homePageCSS.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/static/homePageJS.js"> </script>
</head>
<body>

<div class="box">
 <h1> Welcome </h1>
    <br>
    <form action = "/home" method="post">

        <input type="text" name="ID" value="ID" onfocus="field_focus(this, 'ID');" onblur="field_blur(this, 'ID');" class="ID">
        <input type="password" name="pass" value="Password" onfocus="field_focus(this, 'Password');" onblur="field_blur(this, 'Password');" class="Password">

        <br>
        <div class="submit">
        <input type="submit" value = "Sign In" name="submit" class="submitButton">
        </div>

        <a href="/pr"><div class="buttonOne">Sign Up As Patient</div></a>
        <a href="/dr"><div class="buttonTwo">Sign Up As Doctor</div></a>
    </form>

 <br>
    <%if(request.getAttribute("message") != null){%>
    <%=request.getAttribute("message")%>
    <%}%>

</div>

</body>
</html>