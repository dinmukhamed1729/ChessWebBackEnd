import {getStompClient} from './stompClient.js'
const boardElement = document.getElementById("board");
const columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
const rows = ['8', '7', '6', '5', '4', '3', '2', '1'];

try {
    const response = await fetch(`${window.location.origin}/game/board/${localStorage.gameId}`)
    if (!response.ok) throw new Error(`Ошибка регистрации игры: ${response.statusText}`);
    const jsonResponse = await response.json();
    const board = jsonResponse.pieces;
    console.log('board', board);
    renderBoard();
    console.log("bordRendered")
    renderPiecesPosition(board);
} catch (error) {
    console.error('Ошибка получения доски по gameId:', error);
}

const userStompClient = getStompClient('queue', (message) => {
    message = JSON.parse(message.body);
    console.log(message);

    const oldPosition = message.pieceOldPosition;
    const newPosition = message.pieceNewPosition;

    // Получаем элемент фигуры
    const pieceElement = document.getElementById(message.pieceId);

    // Удаляем фигуру из старой позиции
    const oldPositionElement = document.getElementById(oldPosition);
    if (oldPositionElement && oldPositionElement.contains(pieceElement)) {
        oldPositionElement.removeChild(pieceElement);
    }

    // Перемещаем фигуру в новую позицию
    const newPositionElement = document.getElementById(newPosition);
    if(newPosition.firstChild) {
        newPositionElement.removeChild(newPosition.firstChild);
    }
    if (newPositionElement) {
        newPositionElement.appendChild(pieceElement);
    }
});

function renderPiecesPosition(board) {
    Object.entries(board).forEach(([key, value]) => {
        console.log(key,value)
        let piece = document.createElement("div");
        piece.className = "piece";
        piece.id = `piece-${key}`;
        piece.innerHTML = value.pieceSymbol;
        piece.draggable = true;
        piece.ondragstart = (event) => {
            event.dataTransfer.setData("pieceOldPosition", piece.parentElement.id)
            event.dataTransfer.setData("pieceId", piece.id)
        }
        document.getElementById(key).appendChild(piece);
    });
}

function renderBoard() {
    rows.forEach((row, rowIndex) => {
        columns.forEach((col, colIndex) => {
            const square = document.createElement("div");
            square.id = col + " " + row;
            square.className = "square";
            square.classList.add((colIndex + rowIndex) % 2 === 0 ? 'black' : 'white');

            square.ondragover = (event) => {
                event.preventDefault();
            }

            square.ondrop = (event) => {
                event.preventDefault();

                let pieceId = event.dataTransfer.getData('pieceId');
                let piece = document.getElementById(pieceId);
                let oldPositionId = event.dataTransfer.getData("pieceOldPosition");

                let oldPositionElement = document.getElementById(oldPositionId);

                if(event.target.id === piece.parentElement.id || event.target.id === piece.id)
                    return


                if (!event.target.classList.contains('piece')) {
                    // Создаем новую фигуру вместо перемещения старой
                    if (oldPositionElement && oldPositionElement.contains(piece)) {
                        oldPositionElement.removeChild(piece);
                    }

                    if (event.target.hasChildNodes()) {
                        event.target.replaceChild(piece, event.target.firstChild);
                    } else {
                        event.target.appendChild(piece);  // Если в клетке пусто, добавляем новую фигуру

                    }
                    userStompClient.send('/app/GameSocket', {}, JSON.stringify({
                        pieceOldPosition: oldPositionId,
                        pieceNewPosition: event.target.id,
                        pieceId: piece.id,
                        gameId: localStorage.getItem("gameId"),
                        opponent: localStorage.getItem("username")
                    }));
                }
                else {

                    oldPositionElement.removeChild(piece);
                    event.target.firstChild.replaceWith(piece);
                    userStompClient.send('/app/GameSocket', {}, JSON.stringify({
                        pieceOldPosition: oldPositionId,
                        pieceNewPosition: event.target.parentElement.id,
                        pieceId: piece.id,
                        gameId: localStorage.getItem("gameId"),
                        opponent: localStorage.getItem("username")
                    }));
                }
            }


            boardElement.appendChild(square);
        });
    });
}




