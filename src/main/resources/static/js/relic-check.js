const refreshButton = document.getElementById("refresh-cache");
function refresh() {
    const refreshMesg = document.createElement("label");
    refreshMesg.textContent = "刷新中......";
    refreshButton.parentNode.insertBefore(refreshMesg, refreshButton.nextSibling);
    fetch("http://localhost:8088/relic-check/refresh")
        .then((response) => {
            refreshMesg.textContent = "刷新完成";
        });
}
refreshButton.addEventListener("click", refresh);

const resetButton = document.getElementById("reset-checkbox")
function reset() {
    const inputList = document.querySelectorAll("input[type=checkbox]");
    for(input of inputList) {
        input.checked = false;
    }
    const textList = document.querySelectorAll("input[type=text]");
    for(text of textList) {
        text.value = "";
    }
    const resultTable = document.getElementById("result-table");
    for(elem of resultTable.children) {
        elem.remove();
    }
}
resetButton.addEventListener("click", reset);

const headType = 1;
const handType = 2;
const bodyType = 3;
const feetType = 4;
const sphereType = 5;
const ropeType = 6;
const cavernRelics = 1;
const planarOrnaments = 2;
const halfSetDemand = 1;
const allSetDemand = 2;

document.addEventListener("DOMContentLoaded", () => {
    function loadRelicData(rawData) {
        const relicDiv = document.getElementById("relic-set").children[1];
        for(relicData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = relicData.relicSetName;
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
            const checkboxElement = document.createElement("input");
            checkboxElement.type = "checkbox";
            labelElem.appendChild(checkboxElement);
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
            const textElement = document.createElement("input");
            textElement.type = "text";
            textElement.className = "stat-value";
            labelElem.appendChild(textElement);
            subStatDiv.appendChild(labelElem);
        }
    }

    fetch("http://localhost:8088/relic-set/all")
        .then((response) => {
            console.log("in fetch method")
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadRelicData(json));

    fetch("http://localhost:8088/stat/main-stats")
        .then((response) => {
            console.log("in fetch method")
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadMainStats(json));

    fetch("http://localhost:8088/stat/sub-stats")
        .then((response) => {
            console.log("in fetch method")
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadSubStats(json));
});

function genCheckData() {
    const checkData = {}
    const relicSetList = []
    const relicTypeList = []
    const mainStatList = []
    const subStatList = []

    const relicLevelLabelList = document.getElementById("relic-level-div").children[1].children;
    for(relicLevelLabel of relicLevelLabelList) {
        checkElem = relicLevelLabel.children[0];
        if(checkElem.checked === true) {
            checkData.relicLevel = parseInt(checkElem.dataset.level);
        }
    }

    const relicSetLabelList = document.getElementById("relic-set").children[1].children;
    for(relicSetLabel of relicSetLabelList) {
        if(relicSetLabel.children[0].checked === true) {
            relicSetList.push(relicSetLabel.textContent);
        }
    }

    const relicTypeLabelList = document.getElementById("relic-type").children[1].children;
    for(relicTypeLabel of relicTypeLabelList) {
        if(relicTypeLabel.children[0].checked === true) {
            relicTypeList.push(relicTypeLabel.textContent);
        }
    }

    const mainStatLabelList = document.getElementById("main-stat").children[1].children;
    for(mainStatLabel of mainStatLabelList) {
        if(mainStatLabel.children[0].checked === true) {
            mainStatList.push(mainStatLabel.textContent);
        }
    }

    const subStatLabelList = document.getElementById("sub-stat").children[1].children;
    for(subStatLabel of subStatLabelList) {
        if(subStatLabel.children[0].checked === true) {
            let statValue = {}
            statValue.statName = subStatLabel.textContent;
            statValue.value = subStatLabel.children[1].value;
            subStatList.push(statValue);
        }
    }

    checkData.relicSetList = relicSetList;
    checkData.relicTypeList = relicTypeList;
    checkData.mainStatList = mainStatList;
    checkData.subStatList = subStatList;

    return checkData;
}

function showResult(resultData) {
    const resultTableDiv = document.getElementById("result-table");
    resultTableDiv.innerHTML = "<table><thead><tr><th style=\"width: 100px;\" align=\"left\">角色名</th>\n" +
        "                <th style=\"width: 300px;\" align=\"left\">主词条适配性</th>\n" +
        "                <th style=\"width: 250px;\" align=\"left\">套装效果适配性</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">副词条适配度</th>\n" +
        "                <th style=\"width: 500px;\" align=\"left\">副词条适配详情</th></tr></thead></table>"
    const resultTable = resultTableDiv.children[0];

    for(checkVO of resultData) {
        const charNameTd = document.createElement("td");
        const nameRef = document.createElement("a");
        nameRef.textContent = checkVO.characterName;
        nameRef.href = "http://localhost:8088/character/" + checkVO.characterId;
        charNameTd.appendChild(nameRef);

        const mainStatTd = document.createElement("td");
        const mainStatPara = document.createElement("p");
        mainStatPara.textContent = checkVO.mainStatFitMsg;
        if(checkVO.isMainStatRight === 1) {
            mainStatPara.className = "high-fit";
        }
        mainStatTd.appendChild(mainStatPara);

        const relicSetTd = document.createElement("td");
        const relicSetPara = document.createElement("p");
        relicSetPara.innerHTML = checkVO.relicFitMsg.replace(/\s/g, '<br>');
        if(checkVO.isRelicSetRight === 1) {
            relicSetPara.className = "high-fit";
        }
        relicSetTd.appendChild(relicSetPara);

        const statFitnessTd = document.createElement("td");
        const statFitnessPara = document.createElement("p");
        statFitnessPara.textContent = checkVO.statFitness;
        if(checkVO.statFitness >= 0.65) {
            statFitnessPara.className = "high-fit";
        } else if(checkVO.statFitness >= 0.5) {
            statFitnessPara.className = "middle-fit";
        }
        statFitnessTd.appendChild(statFitnessPara);

        const statFitMsgTd = document.createElement("td");
        const statFitMsgPara = document.createElement("p");
        statFitMsgPara.innerHTML = checkVO.statFitMsg.replace(/\s/g, '<br>');
        statFitMsgTd.appendChild(statFitMsgPara);

        const tBody = document.createElement("tbody");
        const tr = document.createElement("tr");
        tr.appendChild(charNameTd);
        tr.appendChild(mainStatTd);
        tr.appendChild(relicSetTd);
        tr.appendChild(statFitnessTd);
        tr.appendChild(statFitMsgTd);
        tBody.appendChild(tr);
        resultTable.appendChild(tBody);
    }
}

function submitCheck() {
    let checkData = genCheckData();

    fetch("http://localhost:8088/relic-check/check", {
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

const checkSubmitButton = document.getElementById("submit-relic-check");
checkSubmitButton.addEventListener("click", submitCheck);
