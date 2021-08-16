<html>
<head>
  <title>New Account</title>
  <link href="/static/SuccessfulRegisteredCSS.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="doctor-image">
  <div class="text">
    <h1 style="font-size:50px">Successfully Booked</h1>
    <p>Congratulations!</p>

    <form action="/loginPT" method = "post">
        <input type = "submit" name = "backToProfile" value = "Return To Profile">
        <input type = "hidden" name = "id" value = <%=(String)request.getAttribute("id")%>>
    </form>
  </div>
</div>
</body>
</html>