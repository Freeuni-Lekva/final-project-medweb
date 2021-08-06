
 var wsUri = "ws://" + document.location.host + document.location.pathname + "/endpoint";
 var websocket = new WebSocket(wsUri);

function sendMessage(){
    const json = JSON.stringify({
        "name": document.getElementById("userName").value,
        "message": document.getElementById("messageText").value
    });
    websocket.send(json);
    document.getElementById("messageText").value = "";

}

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: #aa0d0d;">ERROR:</span> ' + evt.data);
}

const output = document.getElementById("output");

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

websocket.onmessage = function processMessage(message){
    const jsonData = JSON.parse(message.data);
    if(jsonData.message != null) {
        document.getElementById("messagesTextArea").value += jsonData.name+ ":  " + jsonData.message + "\n";
    }
}

