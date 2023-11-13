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

    let characterBuild = {};
    const pathArray = window.location.pathname.split("/");
    const param = pathArray[pathArray.length - 1];
    if(param !== "new")  {
        let req = new XMLHttpRequest();
        req.open("GET", "http://localhost:8088/character/build/" + param, false);
        req.onload = function() {
            characterBuild = JSON.parse(req.responseText);
        }
        req.send(null);
    }

    let characterName = "";
    const relicSet4List = [];
    const relicSet2List = [];
    const planarOrnamentList = [];
    const bodyStatsList = [];
    const feetStatsList = [];
    const sphereStatsList = [];
    const ropeStatsList = [];
    const subStat1List = [];
    const subStat2List = [];
    const subStat3List = [];
    const subStat4List = [];

    if(characterBuild != null && characterBuild.hasOwnProperty('characterName')) {
        const submitButton = document.getElementById("submit-character-build");
        submitButton.hidden = true;

        const updateButton = document.getElementById("update-character-build");
        updateButton.hidden = false;

        if(characterBuild.canRainbowBuild === true) {
            const rainbowBuildPosRatio = document.getElementById("rainbow-builds").children[1].children[0].children[0];
            rainbowBuildPosRatio.checked = true;
            const rainbowBuildNegRatio = document.getElementById("rainbow-builds").children[1].children[1].children[0];
            rainbowBuildNegRatio.checked = false;
        }


        const buildSugTextarea = document.getElementById("build-sug-textarea");
        buildSugTextarea.value = characterBuild.buildSug;

        characterName = characterBuild.characterName;

        for(relicSet of characterBuild.relicSets) {
            if(relicSet.relicSetType === planarOrnaments) {
                planarOrnamentList.push(relicSet.relicSetName);
            } else if(relicSet.effectDemand === allSetDemand) {
                relicSet4List.push(relicSet.relicSetName);
            } else {
                relicSet2List.push(relicSet.relicSetName);
            }
        }

        for(mainStat of characterBuild.mainStats) {
            if(mainStat.relicType === bodyType) {
                bodyStatsList.push(mainStat.statName);
            } else if(mainStat.relicType === feetType) {
                feetStatsList.push(mainStat.statName);
            } else if(mainStat.relicType === sphereType) {
                sphereStatsList.push(mainStat.statName);
            } else if(mainStat.relicType === ropeType) {
                ropeStatsList.push(mainStat.statName);
            }
        }

        for(subStat of characterBuild.subStats) {
            if(subStat.priority === 1) {
                subStat1List.push(subStat.statName);
            } else if(subStat.priority === 2) {
                subStat2List.push(subStat.statName);
            } else if(subStat.priority === 3) {
                subStat3List.push(subStat.statName);
            } else if(subStat.priority === 4) {
                subStat4List.push(subStat.statName);
            }
        }
    }

    const nameLabel = document.getElementById("character-name");
    nameLabel.value = characterName;

    function loadRelicData(rawData) {
        const relic4Div = document.getElementById("cavern-relic-set-4").children[1];
        for(relicData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = relicData.relicSetName;
            labelElem.className = "relic";
            const inputElement = document.createElement("input");
            inputElement.type = "checkbox";
            if(relicSet4List.includes(relicData.relicSetName)) {
                inputElement.checked = true;
            }
            labelElem.appendChild(inputElement);
            if(relicData.relicSetType === cavernRelics) {
                relic4Div.appendChild(labelElem);
            }
        }

        const relic2Div = document.getElementById("cavern-relic-set-2").children[1];
        for(relicData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = relicData.relicSetName;
            labelElem.className = "relic";
            const inputElement = document.createElement("input");
            inputElement.type = "checkbox";
            if(relicSet2List.includes(relicData.relicSetName)) {
                inputElement.checked = true;
            }
            labelElem.appendChild(inputElement);
            if(relicData.relicSetType === cavernRelics) {
                relic2Div.appendChild(labelElem);
            }
        }

        const ornamentDiv = document.getElementById("planar-ornaments").children[1]
        for(relicData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = relicData.relicSetName;
            labelElem.className = "relic";
            const inputElement = document.createElement("input");
            inputElement.type = "checkbox";
            if(planarOrnamentList.includes(relicData.relicSetName)) {
                inputElement.checked = true;
            }
            labelElem.appendChild(inputElement);
            if(relicData.relicSetType === planarOrnaments) {
                ornamentDiv.appendChild(labelElem);
            }
        }
    }

    function loadBodyStats(rawData) {
        const bodyStatDiv = document.getElementById("body-main-stat").children[1];

        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(bodyStatsList.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            bodyStatDiv.appendChild(labelElem);
        }
    }

    function loadFeetStats(rawData) {
        const feetStatDiv = document.getElementById("feet-main-stat").children[1];

        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(feetStatsList.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            feetStatDiv.appendChild(labelElem);
        }
    }

    function loadSphereStats(rawData) {
        const sphereStatDiv = document.getElementById("sphere-main-stat").children[1];

        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(sphereStatsList.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            sphereStatDiv.appendChild(labelElem);
        }
    }

    function loadRopeStats(rawData) {
        const ropeStatDiv = document.getElementById("rope-main-stat").children[1];

        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(ropeStatsList.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            ropeStatDiv.appendChild(labelElem);
        }
    }

    function loadSubStats(rawData) {
        const subStat1Div = document.getElementById("1-level-stat").children[1];
        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(subStat1List.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            subStat1Div.appendChild(labelElem.cloneNode(true));
        }

        const subStat2Div = document.getElementById("2-level-stat").children[1];
        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(subStat2List.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            subStat2Div.appendChild(labelElem.cloneNode(true));
        }

        const subStat3Div = document.getElementById("3-level-stat").children[1];
        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(subStat3List.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            subStat3Div.appendChild(labelElem.cloneNode(true));
        }

        const subStat4Div = document.getElementById("4-level-stat").children[1];
        for(statData of rawData) {
            const labelElem = document.createElement("label");
            labelElem.textContent = statData.statName;
            labelElem.className = "stat";
            const inputElem = document.createElement("input");
            inputElem.type = "checkbox";
            if(subStat4List.includes(statData.statName)) {
                inputElem.checked = true;
            }
            labelElem.appendChild(inputElem);
            subStat4Div.appendChild(labelElem.cloneNode(true));
        }
    }

    fetch("http://localhost:8088/relic-set/all")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadRelicData(json));

    fetch("http://localhost:8088/stat/body-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadBodyStats(json));

    fetch("http://localhost:8088/stat/feet-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadFeetStats(json));

    fetch("http://localhost:8088/stat/sphere-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadSphereStats(json));

    fetch("http://localhost:8088/stat/rope-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadRopeStats(json));

    fetch("http://localhost:8088/stat/sub-stats")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();
        })
        .then((json) => loadSubStats(json));
});

function genBuildData() {
    const buildData = {};
    const relicSets = [];
    const mainStats = [];
    const subStats = [];

    buildData.characterName = document.getElementById("character-name").value;

    const relicSets4List = document.getElementById("cavern-relic-set-4").children[1].children;
    for (relicElem of relicSets4List) {
        if (relicElem.children[0].checked === true) {
            const relicSet = {};
            relicSet.relicSetName = relicElem.textContent;
            relicSet.relicSetType = cavernRelics;
            relicSet.effectDemand = allSetDemand;
            relicSets.push(relicSet);
        }
    }

    const relicSets2List = document.getElementById("cavern-relic-set-2").children[1].children;
    for (relicElem of relicSets2List) {
        if (relicElem.children[0].checked === true) {
            const relicSet = {};
            relicSet.relicSetName = relicElem.textContent;
            relicSet.relicSetType = cavernRelics;
            relicSet.effectDemand = halfSetDemand;
            relicSets.push(relicSet);
        }
    }

    const ornamentsList = document.getElementById("planar-ornaments").children[1].children;
    for (relicElem of ornamentsList) {
        if (relicElem.children[0].checked === true) {
            const relicSet = {};
            relicSet.relicSetName = relicElem.textContent;
            relicSet.relicSetType = planarOrnaments;
            relicSet.effectDemand = allSetDemand;
            relicSets.push(relicSet);
        }
    }

    const rainbowBuildPosRatio = document.getElementById("rainbow-builds").children[1].children[0].children[0];
    buildData.canRainbowBuild = rainbowBuildPosRatio.checked === true;

    const bodyStatList = document.getElementById("body-main-stat").children[1].children;
    for (statElem of bodyStatList) {
        if (statElem.children[0].checked === true) {
            const mainStat = {}
            mainStat.relicType = bodyType;
            mainStat.statName = statElem.textContent;
            mainStats.push(mainStat);
        }
    }

    const feetStatList = document.getElementById("feet-main-stat").children[1].children;
    for (statElem of feetStatList) {
        if (statElem.children[0].checked === true) {
            const mainStat = {}
            mainStat.relicType = feetType;
            mainStat.statName = statElem.textContent;
            mainStats.push(mainStat);
        }
    }

    const sphereStatList = document.getElementById("sphere-main-stat").children[1].children;
    for (statElem of sphereStatList) {
        if (statElem.children[0].checked === true) {
            const mainStat = {}
            mainStat.relicType = sphereType;
            mainStat.statName = statElem.textContent;
            mainStats.push(mainStat);
        }
    }

    const ropeStatList = document.getElementById("rope-main-stat").children[1].children;
    for (statElem of ropeStatList) {
        if (statElem.children[0].checked === true) {
            const mainStat = {}
            mainStat.relicType = ropeType;
            mainStat.statName = statElem.textContent;
            mainStats.push(mainStat);
        }
    }

    const sub1StatList = document.getElementById("1-level-stat").children[1].children;
    for (statElem of sub1StatList) {
        if (statElem.children[0].checked === true) {
            const subStat = {}
            subStat.statName = statElem.textContent;
            subStat.priority = 1;
            subStats.push(subStat);
        }
    }

    const sub2StatList = document.getElementById("2-level-stat").children[1].children;
    for (statElem of sub2StatList) {
        if (statElem.children[0].checked === true) {
            const subStat = {}
            subStat.statName = statElem.textContent;
            subStat.priority = 2;
            subStats.push(subStat);
        }
    }

    const sub3StatList = document.getElementById("3-level-stat").children[1].children;
    for (statElem of sub3StatList) {
        if (statElem.children[0].checked === true) {
            const subStat = {}
            subStat.statName = statElem.textContent;
            subStat.priority = 3;
            subStats.push(subStat);
        }
    }

    const sub4StatList = document.getElementById("4-level-stat").children[1].children;
    for (statElem of sub4StatList) {
        if (statElem.children[0].checked === true) {
            const subStat = {}
            subStat.statName = statElem.textContent;
            subStat.priority = 4;
            subStats.push(subStat);
        }
    }

    buildData.buildSug = document.getElementById("build-sug-textarea").value;

    buildData.relicSets = relicSets;
    buildData.mainStats = mainStats;
    buildData.subStats = subStats;

    return buildData;
}

function submitBuildData() {
    let buildData = genBuildData();

    fetch("http://localhost:8088/character/build/add", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(buildData),
    }).then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }});
}

function updateBuildData() {
    let buildData = genBuildData();
    const pathArray = window.location.pathname.split("/");
    buildData.characterId = pathArray[pathArray.length - 1];

    fetch("http://localhost:8088/character/build/update", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(buildData),
    }).then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP error: ${response.status}`);
        }});

}




const submitButton = document.getElementById("submit-character-build");
submitButton.addEventListener("click", submitBuildData);

const updateButton = document.getElementById("update-character-build");
updateButton.addEventListener("click", updateBuildData);
