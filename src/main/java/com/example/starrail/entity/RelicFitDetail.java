package com.example.starrail.entity;

public class RelicFitDetail {
    Integer relicFitId;

    Integer relicId;

    Integer relicTypeId;

    String subStatValues;

    Integer relicLevel;

    Integer characterId;

    String characterName;

    Integer relicSetId;

    Integer mainStatId;

    Boolean isMainStatFit;

    Boolean isRelicSetFit;

    Double subStatFitness;

    String subStatFitDesc;

    public Integer getRelicTypeId() {
        return relicTypeId;
    }

    public void setRelicTypeId(Integer relicTypeId) {
        this.relicTypeId = relicTypeId;
    }

    public Integer getRelicLevel() {
        return relicLevel;
    }

    public void setRelicLevel(Integer relicLevel) {
        this.relicLevel = relicLevel;
    }

    public String getSubStatValues() {
        return subStatValues;
    }

    public void setSubStatValues(String subStatValues) {
        this.subStatValues = subStatValues;
    }

    public Integer getRelicFitId() {
        return relicFitId;
    }

    public void setRelicFitId(Integer relicFitId) {
        this.relicFitId = relicFitId;
    }

    public Integer getRelicId() {
        return relicId;
    }

    public void setRelicId(Integer relicId) {
        this.relicId = relicId;
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

    public Integer getRelicSetId() {
        return relicSetId;
    }

    public void setRelicSetId(Integer relicSetId) {
        this.relicSetId = relicSetId;
    }

    public Integer getMainStatId() {
        return mainStatId;
    }

    public void setMainStatId(Integer mainStatId) {
        this.mainStatId = mainStatId;
    }

    public Boolean getMainStatFit() {
        return isMainStatFit;
    }

    public void setMainStatFit(Boolean mainStatFit) {
        isMainStatFit = mainStatFit;
    }

    public Boolean getRelicSetFit() {
        return isRelicSetFit;
    }

    public void setRelicSetFit(Boolean relicSetFit) {
        isRelicSetFit = relicSetFit;
    }

    public Double getSubStatFitness() {
        return subStatFitness;
    }

    public void setSubStatFitness(Double subStatFitness) {
        this.subStatFitness = subStatFitness;
    }

    public String getSubStatFitDesc() {
        return subStatFitDesc;
    }

    public void setSubStatFitDesc(String subStatFitDesc) {
        this.subStatFitDesc = subStatFitDesc;
    }
}
