
function sendMessage(){
    const json = JSON.stringify({
        "name": document.getElementById("name").value,
        "message": document.getElementById("messageText").value
    });
   //websocket.send(json);
    document.getElementById("messageText").value = "";
}