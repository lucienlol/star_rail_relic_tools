package com.example.starrail.vo;

public class CharacterCheckVO implements Comparable<CharacterCheckVO>{

    Integer characterId;

    String characterName;

    // if main stat right, then 1, else 0
    Integer isMainStatRight = 0;

    String mainStatFitMsg;

    // if relic set right, then 1, else 0
    Integer isRelicSetRight = 0;

    String relicFitMsg;

    Double statFitness = 0.0;

    String statFitMsg;

    public String getMainStatFitMsg() {
        return mainStatFitMsg;
    }

    public void setMainStatFitMsg(String mainStatFitMsg) {
        this.mainStatFitMsg = mainStatFitMsg;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getMainStatRight() {
        return isMainStatRight;
    }

    public void setMainStatRight(Integer mainStatRight) {
        isMainStatRight = mainStatRight;
    }

    public Double getStatFitness() {
        return statFitness;
    }

    public void setStatFitness(Double statFitness) {
        this.statFitness = statFitness;
    }

    public String getStatFitMsg() {
        return statFitMsg;
    }

    public void setStatFitMsg(String statFitMsg) {
        this.statFitMsg = statFitMsg;
    }

    public Integer getIsMainStatRight() {
        return isMainStatRight;
    }

    public void setIsMainStatRight(Integer isMainStatRight) {
        this.isMainStatRight = isMainStatRight;
    }

    public Integer getIsRelicSetRight() {
        return isRelicSetRight;
    }

    public void setIsRelicSetRight(Integer isRelicSetRight) {
        this.isRelicSetRight = isRelicSetRight;
    }

    public String getRelicFitMsg() {
        return relicFitMsg;
    }

    public void setRelicFitMsg(String relicFitMsg) {
        this.relicFitMsg = relicFitMsg;
    }

    @Override
    public int compareTo(CharacterCheckVO o) {
        int flagValue = this.isMainStatRight + this.isRelicSetRight;
        Integer objectFlagValue = o.isMainStatRight + o.isRelicSetRight;
        if(flagValue != objectFlagValue) {
            return objectFlagValue.compareTo(flagValue);
        }

        return o.statFitness.compareTo(this.statFitness);
    }
}
