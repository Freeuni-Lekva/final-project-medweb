<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<html>

<head>
    <title>Chat</title>
</head>

<script type = "application/javascript" src = "/static/ForChat.js">
</script>
<body>

<textarea id = "messagesTextArea" readonly = "readonly" rows = "10" cols = "45"></textarea>
<br>

<%  String type =(String)request.getAttribute("type");
String name = "";
if(type.equals("patient")){
    Patient patient = (Patient)request.getAttribute("user");
    name = patient.getName();
} else {
    Doctor doctor = (Doctor) request.getAttribute("user");
    name = doctor.getName();
}
%>
<input type = "text" id = "messageText" size = "35"/>
<input type = "hidden" name = "userName" id = "userName" value = <%=name%> />
<input type = "hidden" name = "recipient" id = "recipientID" value = <%=(String)request.getAttribute("recipientID")%> />
<input type = "hidden" name = "sender" id = "senderID" value = <%=(String)request.getAttribute("senderID")%>/>
<input type = "button" value = "Send" onclick="sendMessage();"/>
<p id="output"></p>

</body>
</html>