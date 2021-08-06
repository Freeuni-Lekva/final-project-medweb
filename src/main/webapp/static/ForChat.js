
function sendMessage(){
    const json = JSON.stringify({
        "name": document.getElementById("name").value,
        "message": document.getElementById("messageText").value
    });
    websocket.send(json);
    document.getElementById("messageText").value = "";
}

var wsUri = "ws://" + document.location.host + document.location.pathname + "chatEndPoint";
var websocket = new WebSocket(wsUri);

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: #aa0d0d;">ERROR:</span> ' + evt.data);
}

const output = document.getElementById("output");

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

