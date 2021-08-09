
 var wsUri = "ws://" + document.location.host + document.location.pathname + "/endpoint";
 var websocket = new WebSocket(wsUri);


function sendMessage(){
    let imageName = document.getElementById("file-input").value;
    var imageNameParsed = imageName.substr(12);
    if(imageName === ""){
        imageNameParsed = "No Image";
    }

    const json = JSON.stringify({
        "name": document.getElementById("userName").value,
        "message": document.getElementById("messageText").value,
        "recipientID": document.getElementById("recipientID").value,
        "senderID": document.getElementById("senderID").value,
        "image": imageNameParsed
    });

    websocket.send(json);
    document.getElementById("messageText").value = "";
    document.getElementById("file-input").value = "";
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
     const messages = document.getElementById("messages");

     let noImage = "No Image";
    const jsonData = JSON.parse(message.data);

    if(jsonData.image && jsonData.image !== noImage){
        let imagePath = returnImagePath(jsonData.image);

    }

    if(jsonData.message != null) {
        appendMessage(jsonData.name+ ":  " + jsonData.message + "\n");
    }

        messages.scrollTop = messages.scrollHeight;
}

 function appendMessage(message){
    const messages = document.getElementById("messages");
    let node = document.createElement("DIV");
    node.innerHTML = message;
    node.classList.add("message");
    messages.appendChild(node);
}

function returnImagePath(image){
     let head = "resources/ToSendInChat/";
     let fullPath = head + image;
     return fullPath;
}





