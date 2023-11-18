document.addEventListener("DOMContentLoaded", () => {

    function createRelicList(relicList) {
        const relicTable = document.getElementById("relic-entity-table").children[0];

        for(relicEntity of relicList) {
            const relicTd = document.createElement("td");
            const relicRef = document.createElement("a");
            relicRef.textContent = relicEntity.relicId;
            relicRef.href = "http://localhost:8088/relic/" + relicEntity.relicId;
            relicTd.appendChild(relicRef);

            const relicSetNameTd = document.createElement("td");
            const relicSetNamePara = document.createElement("p");
            relicSetNamePara.textContent = relicEntity.relicSetList[0];
            relicSetNameTd.appendChild(relicSetNamePara);

            const relicTypeNameTd = document.createElement("td");
            const relicTypeNamePara = document.createElement("p");
            relicTypeNamePara.textContent = relicEntity.relicTypeList[0];
            relicTypeNameTd.appendChild(relicTypeNamePara);

            const relicLevelTd = document.createElement("td");
            const relicLevelPara = document.createElement("p");
            relicLevelPara.textContent = relicEntity.relicLevel;
            relicLevelTd.appendChild(relicLevelPara);

            const relicMainStatTd = document.createElement("td");
            const relicMainStatPara = document.createElement("p");
            relicMainStatPara.textContent = relicEntity.mainStatList[0];
            relicMainStatTd.appendChild(relicMainStatPara);


            const relicSubStatTd = document.createElement("td");
            const relicSubStatPara = document.createElement("p");

            let innerHtml = "";
            for(subStat of relicEntity.subStatList) {
                innerHtml = innerHtml + subStat.statName + ":" + subStat.value + "<br>";
            }
            innerHtml.substring(0, innerHtml.length - 4);
            relicSubStatPara.innerHTML = innerHtml;
            relicSubStatTd.appendChild(relicSubStatPara);

            const relicOpTd = document.createElement("td");
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "删除";
            deleteButton.addEventListener("click", e => {
                deleteRelic(e.target);
            });
            relicOpTd.appendChild(deleteButton);

            const tBody = document.createElement("tbody");
            const tr = document.createElement("tr");
            tr.appendChild(relicTd);
            tr.appendChild(relicSetNameTd);
            tr.appendChild(relicTypeNameTd);
            tr.appendChild(relicLevelTd);
            tr.appendChild(relicMainStatTd);
            tr.appendChild(relicSubStatTd);
            tr.appendChild(relicOpTd);
            tBody.appendChild(tr);

            relicTable.appendChild(tBody);
        }
    }

    fetch("http://localhost:8088/relic/api/all")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();})
        .then((json) => createRelicList(json));
});

function deleteRelic(element) {
    const relicRef = element.parentNode.parentNode.children[0].children[0];
    relicId = relicRef.textContent;

    fetch("http://localhost:8088/relic/api/delete/" + relicId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
        })
}