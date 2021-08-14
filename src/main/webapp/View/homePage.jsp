<html>
<head>
    <title>Login</title>

</head>
<body>

<div class="box">
 <h1> Login </h1>
    <br>
    <form action = "/home" method="post">

        <input type="text" name="ID" value="ID" onfocus="field_focus(this, 'ID');" onblur="field_blur(this, 'ID');" class="ID">
        <input type="password" name="pass" value="Password" onfocus="field_focus(this, 'Password');" onblur="field_blur(this, 'Password');" class="Password">

        <br>
        <br>
        <div class="submit">
        <input type="submit" value = "LOGIN" name="submit">
        </div>

        <a href="/pr"><div class="buttonOne">Sign Up As Patient</div></a>
        <a href="/dr"><div class="buttonTwo">Sign Up As Doctor</div></a>
    </form>


 <br>
    <%if(request.getAttribute("message") != null){%>
    <%=request.getAttribute("message")%>
    <%}%>

</div>

<style>

    body {
        font-family: 'Open Sans', sans-serif;
        background-image: url("https://cdn.wallpapersafari.com/34/41/WG4Hvm.jpg");
        background-color: #cccccc;
        margin: 0 auto 0 auto;
        width:100%;
        text-align:center;
        margin: 20px 0px 20px 0px;
        background-repeat: no-repeat;
    }

    h1 {
        font-size:1.5em;
        color:#525252;
    }

    .box{
        background:white;
        width:300px;
        border-radius:6px;
        margin: 0 auto 0 auto;
        padding:0px 0px 70px 0px;
        border: #2980b9 4px solid;
    }

    .ID{
        background:#ecf0f1;
        border: #ccc 1px solid;
        border-bottom: #ccc 2px solid;
        padding: 8px;
        width:250px;
        color:#AAAAAA;
        margin-top:10px;
        font-size:1em;
        border-radius:4px;
    }

    .Password{
        background:#ecf0f1;
        border: #ccc 1px solid;
        border-bottom: #ccc 2px solid;
        padding: 8px;
        width:250px;
        color:#AAAAAA;
        margin-top:10px;
        font-size:1em;
        border-radius:4px;
    }

    .submit{
        padding:5px 15px;
        border:0 none;
        cursor:pointer;
        -webkit-border-radius: 5px;
        border-radius: 5px;
    }

    .buttonOne{
        background:#2ecc71;
        width:125px;
        padding-top:5px;
        padding-bottom:5px;
        color:white;
        border-radius:4px;
        border: #27ae60 1px solid;

        margin-top:20px;
        margin-bottom:20px;
        float:left;
        margin-left:16px;
        font-weight:800;
        font-size:0.8em;
    }

    .buttonOne:hover{
        background:#2CC06B;
    }


    .buttonTwo{
        float:left;
        background:#3498db;
        width:125px;  padding-top:5px;
        padding-bottom:5px;
        color:white;
        border-radius:4px;
        border: #2980b9 1px solid;

        margin-top:20px;
        margin-bottom:20px;
        margin-left:10px;
        font-weight:800;
        font-size:0.8em;
    }

    .buttonTwo:hover{
        background:#3594D2;
    }

</style>

<script>

    function field_focus(field, ID) {
        if(field.value == ID) {
            field.value = '';
        }
    }

    function field_blur(field, ID) {
        if(field.value == '') {
            field.value = ID;
        }
    }

    function field_focus(field, Password) {
        if(field.value == Password) {
            field.value = '';
        }
    }

    function field_blur(field, Password) {
        if(field.value == '') {
            field.value = Password;
        }
    }

    //Fade in dashboard box
    $(document).ready(function(){
        $('.box').hide().fadeIn(1000);
    });

    //Stop click event
    $('a').click(function(event){
        event.preventDefault();
    });

</script>

</body>


</html>