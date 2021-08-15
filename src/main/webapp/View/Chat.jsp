<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<html>

<head>
    <title>Chat</title>
</head>

<script type = "application/javascript" src = "/static/ForChat.js">
</script>
<link rel="stylesheet" type="text/css" href="/static/Chat.css" />
<body>

<%  String type =(String)request.getAttribute("type");
String name = "";
String head = "http://localhost:8080/";
String urlToForward = "";
if(type.equals("patient")){
    Patient patient = (Patient)request.getAttribute("user");
    urlToForward =head +  "loginPT";
    name = patient.getName();
} else {
    Doctor doctor = (Doctor) request.getAttribute("user");
    urlToForward =head+ "loginDc";
    name = doctor.getName();
}
%>
    <input type = "hidden" name = "userName" id = "userName" value = <%=name%> />
    <input type = "hidden" name = "recipient" id = "recipientID" value = <%=(String)request.getAttribute("recipientID")%> />
    <input type = "hidden" name = "sender" id = "senderID" value = <%=(String)request.getAttribute("senderID")%>/>
    <input type = "hidden" name = "UserType" id = "UserType" value = <%=type%>>
<br>

<%--Try New Chat Scrollbar--%>

<div class = "messages" id = "messages" style="overflow-y: scroll; height: 200px; width:300px; overflow-x: hidden; background: transparent;">

</div>

    <input type = "text" id = "messageText" size = "35"/>
    <input type = "button" value = "Send" onclick="sendMessage();"/>
    <input type="file" accept="image/*" id="file-input" />
<br>

<form action =<%=urlToForward%> method="get">
    <input type = "submit" value="Return To Profile">
    <input type="hidden" name = "id" value=<%=(String)request.getAttribute("senderID")%>>
</form>

</body>
</html>
