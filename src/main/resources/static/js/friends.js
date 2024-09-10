
const socket = new SockJS('/ws');
const userStompClient = Stomp.over(socket);

userStompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    userStompClient.subscribe('/user/queue/private', function (message) {
        console.log("Получено личное сообщение: " + message.body);
    });
});
const userList = document.getElementById("users-list")
fetch(`${window.location.origin}/friendList`)
    .then(response => {
        console.log(response.status)
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(x =>{
            const userItem = document.createElement('div')
            const nameSpan = document.createElement('span')
            const sendButton = document.createElement('button')
            sendButton.className = "add-button"
            nameSpan.className = 'user-name'
            userItem.className = "user-item"
            nameSpan.innerText = x.name
            userItem.appendChild(sendButton)
            userItem.appendChild(nameSpan)
            userList.appendChild(userItem)

            sendButton.onclick =()=>{
                userStompClient.send('/app/sendToUserWithUserName',{},JSON.stringify({message: "Сообщение от друга", sendTo: x.name}))
            }
        })
    })
    .catch(err => console.error(err));