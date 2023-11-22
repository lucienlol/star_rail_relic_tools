package com.example.starrail.vo;

public class RelicFitDetailVO {

    Integer relicFitId;

    Integer relicId;

    String characterName;

    Integer relicLevel;

    String relicType;

    String relicSetName;

    String mainStatName;

    Boolean isMainStatFit;

    Boolean isRelicSetFit;

    Double subStatFitness;

    String subStatFitDesc;

    String subStatValues;

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

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getRelicSetName() {
        return relicSetName;
    }

    public void setRelicSetName(String relicSetName) {
        this.relicSetName = relicSetName;
    }

    public String getMainStatName() {
        return mainStatName;
    }

    public void setMainStatName(String mainStatName) {
        this.mainStatName = mainStatName;
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

    public Integer getRelicLevel() {
        return relicLevel;
    }

    public void setRelicLevel(Integer relicLevel) {
        this.relicLevel = relicLevel;
    }

    public String getRelicType() {
        return relicType;
    }

    public void setRelicType(String relicType) {
        this.relicType = relicType;
    }

}
