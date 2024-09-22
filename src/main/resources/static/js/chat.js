

import {getStompClient} from "./stompClient.js";

const stompClient = getStompClient('all',(jsonMessage)=>{
    console.log('Received message: ', jsonMessage.body);
    jsonMessage = JSON.parse(jsonMessage.body)

    const newMessage = document.createElement('div');
    newMessage.className = "message"
    newMessage.textContent = jsonMessage.principal + ": " + jsonMessage.message;

    output.appendChild(newMessage);
})





function sendMessage() {
    const message = document.getElementById('inputField').value.trim()
    if (message) {
        inputField.value = '';
        stompClient.send("/app/chat.sendMessage", {}, message);
    }
}



document.getElementById("send-btn").onclick = function () {
    sendMessage()
}
document.getElementById('inputField').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
});
