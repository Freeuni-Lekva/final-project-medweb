<%@ page import="freeuni.edu.ge.Models.Patient" %>
<%@ page import="freeuni.edu.ge.Models.Doctor" %>
<html>

<head>
    <title>Chat</title>
</head>

<script type = "application/javascript" src = "/static/ForChat.js">
</script>
<body>

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
    <input type = "hidden" name = "userName" id = "userName" value = <%=name%> />
    <input type = "hidden" name = "recipient" id = "recipientID" value = <%=(String)request.getAttribute("recipientID")%> />
    <input type = "hidden" name = "sender" id = "senderID" value = <%=(String)request.getAttribute("senderID")%>/>
<br>

<%--Try New Chat Scrollbar--%>

<div id = "messages" style="overflow-y: scroll; height: 200px; width:300px; overflow-x: hidden; background: transparent;">

</div>

    <input type = "text" id = "messageText" size = "35"/>
    <input type = "button" value = "Send" onclick="sendMessage();"/>
    <input type="file" accept="image/*" id="file-input" />


</body>
</html>
