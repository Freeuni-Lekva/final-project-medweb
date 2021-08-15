
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
        "type": document.getElementById("UserType").value,
        "senderID": document.getElementById("senderID").value,
        "image": imageNameParsed
    });

    websocket.send(json);
    document.getElementById("messageText").value = "";
    document.getElementById("file-input").value = "";
}

websocket.onerror = function(evt) { onError(evt) };

function onError(evt) {
    writeToScreen('<span style="color: #c10808;">ERROR:</span> ' + evt.data);
}

const output = document.getElementById("output");

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}


 websocket.onmessage = function processMessage(message){
     const messages = document.getElementById("messages");

     let noImage = "No Image";
    const jsonData = JSON.parse(message.data);


    if(jsonData.message != null) {
        appendMessage(jsonData.name+ ":  " + jsonData.message + "\n", jsonData.type);
    }

    if(jsonData.image && jsonData.image !== noImage){
        let imagePath = returnImagePath(jsonData.image);
        appendImage(imagePath);
    }
        messages.scrollTop = messages.scrollHeight;
}

 function appendMessage(message, type){
    const messages = document.getElementById("messages");
    let node = document.createElement("DIV");
    node.innerHTML = message;
    if(type && type !== ""){
        if(type === "patient"){
            node.classList.add("patient-message")
        }

        if(type === "doctor"){
            node.classList.add("doctor-message")
        }
    } else {
        node.classList.add("program-message");
    }
    messages.appendChild(node);
}

function appendImage(imagePath){
    const messages = document.getElementById("messages");
    let node = document.createElement("IMG");
    node.src = imagePath;
    node.width = "100";
    node.height = "100";
    messages.appendChild(node);
}

function returnImagePath(image){
     let head = "resources/ToSendInChat/";
     let fullPath = head + image;
     return fullPath;
}





