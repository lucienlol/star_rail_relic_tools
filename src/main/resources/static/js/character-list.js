document.addEventListener("DOMContentLoaded", () => {

    function createUserList(userList) {
        const charListDiv = document.getElementById("character-list")

        for(userData of userList) {
            const userDiv = document.createElement("div");
            userDiv.className = "character";
            userDiv.id = userData.characterId;
            const userLabel = document.createElement("label");
            userLabel.className = "character-name";
            const userText = document.createElement("b");
            userText.textContent = userData.characterName;
            const userRef = document.createElement("a");
            userRef.href = "http://localhost:8088/character/" + userData.characterId;
            userRef.text = "配装";
            const hideButton = document.createElement("button");
            hideButton.textContent = "隐藏";
            userLabel.appendChild(userText);
            userDiv.appendChild(userLabel);
            userDiv.append(userRef);
            userDiv.append(hideButton);
            charListDiv.appendChild(userDiv);
        }
    }

    fetch("http://localhost:8088/character/all-show")
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error: ${response.status}`);
            }
            return response.json();})
        .then((json) => createUserList(json));
});