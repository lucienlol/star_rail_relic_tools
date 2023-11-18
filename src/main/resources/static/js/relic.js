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

document.addEventListener("DOMContentLoaded", () => {

    let relicData = {};
    const pathArray = window.location.pathname.split("/");
    const param = pathArray[pathArray.length - 1];
    if(param !== "new")  {
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
            throw new Error(`HTTP error: ${response.status}`);
        }
        return response.json();
    })
        .then((json) => showResult(json));
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
            throw new Error(`HTTP error: ${response.status}`);
        }});

}

const updateButton = document.getElementById("update-relic-entity");
updateButton.addEventListener("click", updateEntity);