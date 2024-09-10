

const socket = new SockJS(`${window.location.origin}/ws`);
const stompClient = Stomp.over(socket);
const output = document.getElementById('output')
stompClient.connect({}, (frame) => {
    console.log("Connected: " + frame);
    stompClient.subscribe('/topic/public', (jsonMessage) => {
        console.log('Received message: ', jsonMessage.body);
        jsonMessage = JSON.parse(jsonMessage.body)

        const newMessage = document.createElement('div');
        newMessage.className = "message"
        newMessage.textContent = jsonMessage.principal + ": " + jsonMessage.message;

        output.appendChild(newMessage);
    });
});

document.getElementById("send-btn").onclick = function () {
    sendMessage()
}
document.getElementById('inputField').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
});

function sendMessage() {
    const message = document.getElementById('inputField').value.trim()
    if (message) {
        inputField.value = '';
        stompClient.send("/app/chat.sendMessage", {}, message);
    }
}
