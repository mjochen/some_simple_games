const API = "http://localhost:8080/game"; // adjust if needed
const diceOrder = ["white1", "white2", "yellow", "red", "green", "blue"];
const colorOrder = ["yellow", "red", "green", "blue"];

async function newGame() {
    var comp = document.getElementById("complexGame");
    console.log(comp.checked)
    const res = await fetch(`${API}/newGame?complex=${comp.checked}`, { method: "POST" });
    const scorecard = await res.json();
    updateScoreCard(scorecard);
}

async function fetchScoreCard() {
    console.log("NOT USING THIS ONE ANY MORE!")
    const res = await fetch(API + "/scorecard");
    const scorecard = await res.json();
    // console.log(scorecard);
    updateScoreCard(scorecard);
}

function updateScoreCard(sc) {
    const c1 = document.getElementById("diceContainer");
    c1.innerHTML = "";
    const c2 = document.getElementById("optionsContainer");
    c2.innerHTML = "";

    let scorecardDiv = document.getElementById("scorecard");
    scorecardDiv.innerHTML = "";

    //for (const [rowName, rowData] of Object.entries(sc)) {
    for (const rowName of colorOrder) {
        rowData = sc[rowName];
        console.log(rowData)
        if (rowName === "penalties") continue; // skip penalties
        console.log(rowName);

        const rowDiv = document.createElement("div");
        // rowDiv.className = `row ${rowName}`;
        rowDiv.className = `row`;

        rowData.numbers.forEach((num, idx) => {
            const cell = document.createElement("div");
            cell.classList.add("cell");
            cell.classList.add(rowName);
            cell.textContent = num;

            if (rowData.ticked[idx]) {
                cell.classList.add("crossed");
            }

            rowDiv.appendChild(cell);
        });

        // add lock cell at the end
        const lockCell = document.createElement("div");
        lockCell.classList.add("cell", "lock");
        lockCell.textContent = rowData.locked ? "🔒" : "🤙";

        rowDiv.appendChild(lockCell);

        scorecardDiv.appendChild(rowDiv);
    }

    // Penalties
    document.getElementById("penalties").textContent = sc.penalties;
}

async function rollDice() {
    const res = await fetch(API + "/roll");
    const dice = await res.json();
    console.log(dice);
    if (dice["white1"] > 0) {
        renderDice(dice);
        const res = await fetch(`${API}/getOptions?white=true`, { method: "POST" });
        const opts = await res.json();
        renderOptions(opts.options, true);
    }
}

async function handleOptionClick(color, value, white) {
    console.log(color, value, white)
    const res = await fetch(`${API}/setOption?color=${color}&number=${value}&white=${white}`, { method: "POST" });
    const scorecard = await res.json();
    updateScoreCard(scorecard);

    if (white) {
        const res = await fetch(`${API}/getOptions?white=false`, { method: "POST" });
        const opts = await res.json();
        renderOptions(opts.options, false);
    }
    else {
        const container = document.getElementById("optionsContainer");
        container.innerHTML = "";
    }
}

function renderDice(dice) {
    const container = document.getElementById("diceContainer");
    container.innerHTML = ""; // clear old dice

    diceOrder.forEach(color => {
        const value = dice[color];
        if (value !== undefined) {
            const die = document.createElement("div");
            die.classList.add("die", color);
            die.textContent = value;
            container.appendChild(die);
        }
    });
}

function renderOptions(options, white) {
    // console.log("okidoki")
    // console.log(options)
    const container = document.getElementById("optionsContainer");
    container.innerHTML = "";

    const subtitle = document.createElement("h3");
    if (white) {
        subtitle.innerText = "Met de witte dobbelstenen:";
    }
    else {
        subtitle.innerText = "Met de gekleurde dobbelstenen:";
    }
    container.appendChild(subtitle);

    const lockCell = document.createElement("div");
    lockCell.classList.add("die", "clickable");
    lockCell.textContent = "❌";
    lockCell.addEventListener("click", () => handleOptionClick("no color", 0, white));
    container.appendChild(lockCell);

    options.forEach(opt => {
        const [color, value] = opt.split(" ");
        const die = document.createElement("div");
        die.className = `die ${color} clickable`;
        die.textContent = value;

        // click handling
        die.addEventListener("click", () => handleOptionClick(color, value, white));

        container.appendChild(die);
    });
}



// async function mark(color, number) {
//     const res = await fetch(`${API}/mark?color=${color}&number=${number}`, { method: "POST" });
//     const state = await res.json();

//     //   renderState(state);
// }



// Initial load
// fetchState();