
const userList = document.getElementById("users-list")
fetch(`${window.location.origin}/user/allUsers`)
    .then(response => {
        console.log(response.status)
        return response.json()
    })
    .then(data => {
        console.log(data)
        data.forEach(x =>{
            const userItem = document.createElement('div')
            const addButton = document.createElement('button')
            const nameSpan = document.createElement('span')
            nameSpan.className = 'user-name'
            userItem.className = "user-item"
            addButton.className = 'add-button'
            nameSpan.innerText = x.name
            addButton.innerText = 'Add'
            userItem.appendChild(nameSpan)
            userItem.appendChild(addButton)
            userList.appendChild(userItem)

            addButton.onclick = () => {


                fetch(`${window.location.origin}/user/addFriend`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({name: x.name})
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.text();
                    })
                    .then(text => {
                        console.log(text);
                    })
                    .catch(error => {
                        console.error('Error during POST request:', error);
                    });
            };
        })
    })
    .catch(err => console.error(err));



