const resetButton = document.getElementById("reset")
function reset() {
    const inputList = document.querySelectorAll("input[type=checkbox]");
    for(input of inputList) {
        input.checked = input.parentNode.textContent === "all";
    }
    const textList = document.querySelectorAll("input[type=text]");
    for(text of textList) {
        text.value = "";
    }
}
resetButton.addEventListener("click", reset);

document.addEventListener("DOMContentLoaded", () => {

    function loadCharacterData(rawData) {
        const characterDiv = document.getElementById("character-div").children[1];
        for(characterData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = characterData.characterName;
            labelElem.className = "character";
            const inputElement = document.createElement("input");
            inputElement.type = "checkbox";
            labelElem.appendChild(inputElement);
            characterDiv.appendChild(labelElem);
        }
    }

    function loadRelicSetData(rawData) {
        const relicDiv = document.getElementById("relic-set").children[1];
        for(relicSetData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = relicSetData.relicSetName;
            labelElem.className = "relic";
            const inputElement = document.createElement("input");
            inputElement.type = "checkbox";
            labelElem.appendChild(inputElement);
            relicDiv.appendChild(labelElem);
        }
    }

    function loadMainStats(rawData) {
        const mainStatDiv = document.getElementById("main-stat").children[1];
        for(mainStatData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = mainStatData.statName;
            labelElem.className = "stat";
            const inputElement = document.createElement("input");
            inputElement.type = "checkbox";
            labelElem.appendChild(inputElement);
            mainStatDiv.appendChild(labelElem);
        }
    }

    function loadSubStats(rawData) {
        const subStatDiv = document.getElementById("sub-stat").children[1];
        for(subStatData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = subStatData.statName;
            labelElem.className = "stat";
            const checkboxElement = document.createElement("input");
            checkboxElement.type = "checkbox";
            labelElem.appendChild(checkboxElement);
            subStatDiv.appendChild(labelElem);
        }
    }

    fetch("http://localhost:8088/character/all-show")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadCharacterData(json));

    fetch("http://localhost:8088/relic-set/all")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadRelicSetData(json));

    fetch("http://localhost:8088/stat/main-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadMainStats(json));

    fetch("http://localhost:8088/stat/sub-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadSubStats(json));
});

function genRelicData() {
    const reqData = {}
    let relics = []
    const characters = []
    const relicLevels = []
    const relicSets = []
    const relicTypes = []
    const mainStats = []
    const subStats = []

    const relicIdInput = document.getElementById("relic-id");
    if(relicIdInput.value !== "") {
        relics = relicIdInput.value.split(",");
    }

    const characterLabelList = document.getElementById("character-div").children[1].children;
    for(characterLabel of characterLabelList) {
        checkElem = characterLabel.children[0];
        if(checkElem.checked === true) {
            if(characterLabel.textContent === "all") {
                break;
            }
            characters.push(characterLabel.textContent);
        }
    }

    const relicLevelLabelList = document.getElementById("relic-level-div").children[1].children;
    for(relicLevelLabel of relicLevelLabelList) {
        checkElem = relicLevelLabel.children[0];
        if(checkElem.checked === true) {
            if(relicLevelLabel.textContent === "all") {
                break;
            }
            relicLevels.push(relicLevelLabel.textContent);
        }
    }

    const relicSetLabelList = document.getElementById("relic-set").children[1].children;
    for(relicSetLabel of relicSetLabelList) {
        checkElem = relicSetLabel.children[0];
        if(checkElem.checked === true) {
            if(relicSetLabel.textContent === "all") {
                break;
            }
            relicSets.push(relicSetLabel.textContent);
        }
    }

    const relicTypeLabelList = document.getElementById("relic-type").children[1].children;
    for(relicTypeLabel of relicTypeLabelList) {
        checkElem = relicTypeLabel.children[0];
        if(checkElem.checked === true) {
            if(relicTypeLabel.textContent === "all") {
                break;
            }
            relicTypes.push(relicTypeLabel.textContent);
        }
    }

    const mainStatLabelList = document.getElementById("main-stat").children[1].children;
    for(mainStatLabel of mainStatLabelList) {
        checkElem = mainStatLabel.children[0];
        if(checkElem.checked === true) {
            if(mainStatLabel.textContent === "all") {
                break;
            }
            mainStats.push(mainStatLabel.textContent);
        }
    }

    const subStatLabelList = document.getElementById("sub-stat").children[1].children;
    for(subStatLabel of subStatLabelList) {
        checkElem = subStatLabel.children[0];
        if(checkElem.checked === true) {
            subStats.push(subStatLabel.textContent);
        }
    }

    reqData.characters = characters;
    reqData.relics = relics;
    reqData.relicLevels = relicLevels;
    reqData.relicTypes = relicTypes;
    reqData.relicSets = relicSets;
    reqData.mainStats = mainStats;
    reqData.subStats = subStats;

    return reqData;
}

function showResult(resultData) {
    const resultTableDiv = document.getElementById("result-table");

    resultTableDiv.innerHTML = "<table><thead><tr><th style=\"width: 100px;\" align=\"left\">遗器id</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">适配角色</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">遗器等级</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">遗器套装名称</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">遗器部位</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">主词条名称</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">套装效果适配性</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">主词条适配性</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">副词条适配度</th>\n" +
        "                <th style=\"width: 500px;\" align=\"left\">副词条适配详情</th></tr></thead></table>"
    const resultTable = resultTableDiv.children[0];

    for(detailVO of resultData) {
        const relicIdTd = document.createElement("td");
        const relicRef = document.createElement("a");
        relicRef.textContent = detailVO.relicId;
        relicRef.href = "http://localhost:8088/relic/" + detailVO.relicId;
        relicIdTd.appendChild(relicRef);

        const charNameTd = document.createElement("td");
        const nameRef = document.createElement("a");
        nameRef.textContent = detailVO.characterName;
        nameRef.href = "http://localhost:8088/character/" + detailVO.characterId;
        charNameTd.appendChild(nameRef);

        const relicLevelTd = document.createElement("td");
        const relicLevelPara = document.createElement("p");
        relicLevelPara.textContent = detailVO.relicLevel;
        relicLevelTd.appendChild(relicLevelPara);

        const relicSetNameTd = document.createElement("td");
        const relicSetNamePara = document.createElement("p");
        relicSetNamePara.textContent = detailVO.relicSetName;
        relicSetNameTd.appendChild(relicSetNamePara);

        const relicTypeTd = document.createElement("td");
        const relicTypePara = document.createElement("p");
        relicTypePara.textContent = detailVO.relicType;
        relicTypeTd.appendChild(relicTypePara);

        const relicMainStatTd = document.createElement("td");
        const relicMainStatPara = document.createElement("p");
        relicMainStatPara.textContent = detailVO.mainStatName;
        relicMainStatTd.appendChild(relicMainStatPara);

        const relicSetFitTd = document.createElement("td");
        const relicSetFitPara = document.createElement("p");
        if(detailVO.relicSetFit) {
            relicSetFitPara.className = "high-fit";
            relicSetFitPara.textContent = "遗器套装符合";
        } else {
            relicSetFitPara.textContent = "遗器套装不符合";
        }
        relicSetFitTd.appendChild(relicSetFitPara);

        const mainStatFitTd = document.createElement("td");
        const mainStatFitPara = document.createElement("p");
        if(detailVO.mainStatFit) {
            mainStatFitPara.className = "high-fit";
            mainStatFitPara.textContent = "主词条符合"
        } else {
            mainStatFitPara.textContent = "主词条不符合"
        }
        mainStatFitTd.appendChild(mainStatFitPara);

        const statFitnessTd = document.createElement("td");
        const statFitnessPara = document.createElement("p");
        statFitnessPara.textContent = detailVO.subStatFitness;
        statFitnessTd.appendChild(statFitnessPara);

        const statFitMsgTd = document.createElement("td");
        const statFitMsgPara = document.createElement("p");
        statFitMsgPara.innerHTML = detailVO.subStatFitDesc.replace(/\s/g, '<br>');
        statFitMsgTd.appendChild(statFitMsgPara);

        const tBody = document.createElement("tbody");
        const tr = document.createElement("tr");
        tr.appendChild(relicIdTd);
        tr.appendChild(charNameTd);
        tr.appendChild(relicLevelTd);
        tr.appendChild(relicSetNameTd);
        tr.appendChild(relicTypeTd);
        tr.appendChild(relicMainStatTd);
        tr.appendChild(relicSetFitTd);
        tr.appendChild(mainStatFitTd);
        tr.appendChild(statFitnessTd);
        tr.appendChild(statFitMsgTd);
        tBody.appendChild(tr);
        resultTable.appendChild(tBody);
    }
}

function getFitList() {
    let checkData = genRelicData();

    fetch("http://localhost:8088/relic-fit/api/list", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(checkData),
    }).then((response) => {
        console.log("in fetch method")
        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }
        return response.json();
    })
        .then((json) => showResult(json));
}

const submitButton = document.getElementById("submit");
submitButton.addEventListener("click", getFitList);