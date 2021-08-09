
 var wsUri = "ws://" + document.location.host + document.location.pathname + "/endpoint";
 var websocket = new WebSocket(wsUri);


function sendMessage(){
    let imageName = document.getElementById("file-input").value;
    let imageNameParsed = imageName.substr(12);

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

const messages = document.getElementById("messages");

websocket.onmessage = function processMessage(message){
    const jsonData = JSON.parse(message.data);
    alert("hereis");
    if(jsonData.message != null) {
        appendMessage(jsonData.name+ ":  " + jsonData.message + "\n");
    }
}

 function appendMessage(message){

    const messages = document.getElementById("messages");
    let node = document.createElement("DIV");
    node.innerHTML = message;
    node.classList.add("message");
    messages.appendChild(node);
}

    // function saveInformation(fileList){
    //
    //     let file = null;
    //     alert("shemovida");
    //     for(let i = 0; i < fileList.length; i++){
    //         if(fileList[i].type.match(/^image\//)){
    //             file = fileList[i];
    //             break;
    //         }
    //     }
    //
    //     alert("change");
    //     alert(file.name);
    //     alert("change2");
    //     if(file != null){
    //         document.getElementById("imgSaver").src = URL.createObjectURL(file);
    //     }
    // }

    //new Chat trying


// function getMessages(){
//     let shouldScroll = messages.scrollTop + messages.clientHeight === messages.scrollHeight;
//     appendMessage();
//
//     if(!shouldScroll){
//         scrollToBottom();
//     }
// }
//
//
// function scrollToBottom() {
//      messages.scrollTop = messages.scrollHeight;
// }
//
//  scrollToBottom();
//
//
//  setInterval(getMessages, 100);
