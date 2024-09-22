import { getStompClient } from './stompClient.js';

const Commands = {
    INVITATION: "INVITATION",
    START_GAME: "START_GAME",
};

// Регистрация игры
async function registerGame(sendTo, from) {
    try {
        const response = await fetch('/game/registerGame', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ sendTo, from })
        });

        if (!response.ok) throw new Error(`Ошибка регистрации игры: ${response.statusText}`);

        const gameId = await response.text();
        console.log(`GameId: ${gameId}`);
        localStorage.setItem("gameId", JSON.parse(gameId).gameId);

        return gameId;
    } catch (error) {
        console.error('Ошибка регистрации игры:', error);
    }
}

// STOMP клиент для обработки сообщений
const userStompClient = getStompClient('queue', async (message) => {
    const { from, COMMAND, gameId } = JSON.parse(message.body);

    switch (COMMAND) {
        case Commands.INVITATION:
            if (confirm(`Приглашение от ${from}`)) {
                const gameId = await registerGame(currentName, from);

                userStompClient.send('/app/sendToUserWithUserName', {}, JSON.stringify({
                    message: "",
                    sendTo: from,
                    from: currentName,
                    COMMAND: Commands.START_GAME,
                    gameId
                }));

                window.location.replace(`${window.location.origin}/pages/game`);
            }
            break;
        case Commands.START_GAME:
            localStorage.setItem("gameId", JSON.parse(gameId).gameId);
            window.location.replace(`${window.location.origin}/pages/game`);
            break;
    }

    localStorage.setItem('username', from);
});

// Получение имени текущего пользователя
async function fetchCurrentUserName() {
    try {
        const response = await fetch(`${window.location.origin}/user/name`);

        if (!response.ok) throw new Error('Не удалось получить имя пользователя');

        const username = await response.text();
        currentName = username;
        localStorage.setItem('currentName', username);
        console.log(`Имя пользователя: ${username}`);
    } catch (error) {
        console.error('Ошибка при получении имени пользователя:', error);
    }
}

// Отображение списка друзей
async function displayFriendList() {
    try {
        const response = await fetch(`${window.location.origin}/user/friendList`);

        if (!response.ok) throw new Error(`Ошибка получения списка друзей: ${response.statusText}`);

        const friends = await response.json();
        const userList = document.getElementById("users-list");

        friends.forEach(({ name }) => {
            const userItem = document.createElement('div');
            userItem.className = "user-item";

            const nameSpan = document.createElement('span');
            nameSpan.className = 'user-currentName';
            nameSpan.innerText = name;

            const sendButton = document.createElement('button');
            sendButton.className = "add-button";
            sendButton.innerText = "Send";

            userItem.append(nameSpan, sendButton);
            userList.appendChild(userItem);

            // Отправка приглашения
            sendButton.onclick = () => {
                userStompClient.send('/app/sendToUserWithUserName', {}, JSON.stringify({
                    message: `Сообщение от друга ${currentName}`,
                    sendTo: name,
                    from: currentName,
                    COMMAND: Commands.INVITATION,
                    gameId: 0
                }));
            };
        });
    } catch (error) {
        console.error('Ошибка при получении списка друзей:', error);
    }
}

let currentName;

// Инициализация данных
async function initialize() {
    await fetchCurrentUserName();
    await displayFriendList();
}

// Запуск инициализации при загрузке страницы
initialize();
