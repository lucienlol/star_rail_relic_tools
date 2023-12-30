const resetButton = document.getElementById("reset-checkbox")
function reset() {
    const inputList = document.querySelectorAll("input[type=checkbox], input[type=ratio]");
    for(input of inputList) {
        input.checked = false;
    }
    const textList = document.querySelectorAll("input[type=text]");
    for(text of textList) {
        text.value = "";
    }
}
resetButton.addEventListener("click", reset);

function genRelicData() {
    const relicData = {}
    const relicSetList = []
    const relicTypeList = []
    const mainStatList = []
    const subStatList = []

    const relicLevelLabelList = document.getElementById("relic-level-div").children[1].children;
    for(relicLevelLabel of relicLevelLabelList) {
        checkElem = relicLevelLabel.children[0];
        if(checkElem.checked === true) {
            relicData.relicLevel = parseInt(relicLevelLabel.textContent);
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

    relicData.relicSetList = relicSetList;
    relicData.relicTypeList = relicTypeList;
    relicData.mainStatList = mainStatList;
    relicData.subStatList = subStatList;

    return relicData;
}

function submitEntity() {
    let checkData = genRelicData();

    fetch("http://localhost:8088/relic/api/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(checkData),
    }).then((response) => {
        console.log("in fetch method")
        if (!response.ok) {
            alert("提交失败！请检查");
            throw new Error(`HTTP error: ${response.status}`);
        }
        return response.json();
    })
        .then((json) => {
            url = "http://localhost:8088/relic/" + json;
            window.location.href = url;
        });
}

const submitButton = document.getElementById("submit-relic-entity");
submitButton.addEventListener("click", submitEntity);

function updateEntity() {
    let relicData = genRelicData();
    const pathArray = window.location.pathname.split("/");
    relicData.relicId = pathArray[pathArray.length - 1];

    fetch("http://localhost:8088/relic/api/update", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(relicData),
    }).then((response) => {
        if (!response.ok) {
            alert("更新失败，请检查！")
            throw new Error(`HTTP error: ${response.status}`);
        }
        location.reload();
    });

}

const updateButton = document.getElementById("update-relic-entity");
updateButton.addEventListener("click", updateEntity);

document.addEventListener("DOMContentLoaded", () => {

    updateButton.hidden = true;
    updateRelicButton.hidden = true;

    let relicData = {};
    const pathArray = window.location.pathname.split("/");
    const param = pathArray[pathArray.length - 1];
    if(param !== "new")  {
        submitButton.hidden = true;
        submitRelicFitButton.hidden = true;
        updateButton.hidden = false;
        updateRelicButton.hidden = false;

        let req = new XMLHttpRequest();
        req.open("GET", "http://localhost:8088/relic/api/" + param, false);
        req.onload = function() {
            relicData = JSON.parse(req.responseText);
        }
        req.send(null);
    }

    let relicLevel = 0;
    const subStatMap = new Map();
    let relicSetList = [];
    let mainStatList = [];

    if(relicData != null && relicData.hasOwnProperty("relicLevel")) {
        const submitButton = document.getElementById("submit-relic-entity");
        submitButton.hidden = true;
        const updateButton = document.getElementById("update-relic-entity");
        updateButton.hidden = false;

        const relicLevelLabelList = document.getElementById("relic-level-div").children[1].children;
        relicLevel = relicData.relicLevel;
        for(relicLevelLabel of relicLevelLabelList) {
            inputElem = relicLevelLabel.children[0];
            if(parseInt(relicLevelLabel.textContent) === relicLevel) {
                inputElem.checked = true;
            }
        }

        const relicTypeLabelList = document.getElementById("relic-type").children[1].children;
        for(relicTypeLabel of relicTypeLabelList) {
            inputElem = relicTypeLabel.children[0];
            if(relicData.relicTypeList.includes(relicTypeLabel.textContent)) {
                inputElem.checked = true;
            }
        }

        for(statValue of relicData.subStatList) {
            subStatMap.set(statValue.statName, statValue.value);
        }

        relicSetList = relicData.relicSetList;
        mainStatList = relicData.mainStatList;
    }


    function loadRelicSetData(rawData) {
        const relicDiv = document.getElementById("relic-set").children[1];
        for(relicSetData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = relicSetData.relicSetName;
            labelElem.className = "relic";
            const inputElement = document.createElement("input");
            inputElement.type = "radio";
            inputElement.name = "relic-set";
            if(relicSetList.includes(relicSetData.relicSetName)) {
                inputElement.checked = true;
            }
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
            inputElement.type = "radio";
            inputElement.name = "main-stat";
            if(mainStatList.includes(mainStatData.statName)) {
                inputElement.checked = true;
            }
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
            const textElement = document.createElement("input");
            textElement.type = "text";
            textElement.className = "stat-value";
            if(subStatMap.has(subStatData.statName)) {
                checkboxElement.checked = true;
                textElement.value = subStatMap.get(subStatData.statName);
            }
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
        .then((json) => loadRelicSetData(json));

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

function showResult(resultData) {
    const resultTableDiv = document.getElementById("result-table");
    resultTableDiv.innerHTML = "<table><thead><tr><th style=\"width: 100px;\" align=\"left\">角色名</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">主词条适配性</th>\n" +
        "                <th style=\"width: 100px;\" align=\"left\">套装效果适配性</th>\n" +
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
        if(checkVO.mainStatFit) {
            mainStatPara.className = "high-fit";
            mainStatPara.textContent = "主词条符合"
        } else {
            mainStatPara.textContent = "主词条不符合"
        }
        mainStatTd.appendChild(mainStatPara);

        const relicSetTd = document.createElement("td");
        const relicSetPara = document.createElement("p");
        if(checkVO.relicSetFit) {
            relicSetPara.className = "high-fit";
            relicSetPara.textContent = "遗器套装符合";
        } else {
            relicSetPara.textContent = "遗器套装不符合";
        }
        relicSetTd.appendChild(relicSetPara);

        const statFitnessTd = document.createElement("td");
        const statFitnessPara = document.createElement("p");
        statFitnessPara.textContent = checkVO.subStatFitness;
        statFitnessTd.appendChild(statFitnessPara);

        const statFitMsgTd = document.createElement("td");
        const statFitMsgPara = document.createElement("p");
        statFitMsgPara.innerHTML = checkVO.subStatFitDesc.replace(/\s/g, '<br>');
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

function checkRelicFit() {
    let checkData = genRelicData();

    fetch("http://localhost:8088/relic-fit/api/relic-check", {
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

const checkRelicFitButton = document.getElementById("check-relic-fit");
checkRelicFitButton.addEventListener("click", checkRelicFit);

function updateRelicAndFit() {
    let relicData = genRelicData();
    const pathArray = window.location.pathname.split("/");
    relicData.relicId = pathArray[pathArray.length - 1];

    fetch("http://localhost:8088/relic/api/update-with-fit", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(relicData),
    }).then((response) => {
        if (!response.ok) {
            alert("更新失败，请检查！")
            throw new Error(`HTTP error: ${response.status}`);
        }
    });
}

const updateRelicButton = document.getElementById("update-relic-fit");
updateRelicButton.addEventListener("click", updateRelicAndFit);

function submitRelicAndFit() {
    let relicData = genRelicData();

    fetch("http://localhost:8088/relic/api/submit-with-fit", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(relicData),
    }).then((response) => {
        if (!response.ok) {
            alert("新建失败，请检查！")
            throw new Error(`HTTP error: ${response.status}`);
        }
        return response.json();
    })
        .then((json) => {
            url = "http://localhost:8088/relic/" + json;
            window.location.href = url;
        });
}

const submitRelicFitButton = document.getElementById("submit-relic-fit");
submitRelicFitButton.addEventListener("click", submitRelicAndFit);

