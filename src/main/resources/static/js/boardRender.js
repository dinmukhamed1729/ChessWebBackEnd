const board = document.getElementById("board");


for (let i = 0; i < 8; i++) {
    for (let j = 0; j < 8; j++) {
        const square = document.createElement("div");
        square.classList.add("square")
        square.classList.add((i + j) % 2 === 0 ? 'white' : 'black')
        square.draggable = true;
        square.innerText = 'B'
        board.appendChild(square)
    }
}


