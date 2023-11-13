package com.example.starrail.vo;

import java.util.List;

public class CharacterBuild {
    Integer characterId;

    String characterName;

    List<MainStatVO> mainStats;

    List<RelicSetVO> relicSets;

    Boolean canRainbowBuild;

    List<SubStatVO> subStats;

    String buildSug;

    public Boolean getCanRainbowBuild() {
        return canRainbowBuild;
    }

    public void setCanRainbowBuild(Boolean canRainbowBuild) {
        this.canRainbowBuild = canRainbowBuild;
    }

    public String getBuildSug() {
        return buildSug;
    }

    public void setBuildSug(String buildSug) {
        this.buildSug = buildSug;
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

    public List<MainStatVO> getMainStats() {
        return mainStats;
    }

    public void setMainStats(List<MainStatVO> mainStats) {
        this.mainStats = mainStats;
    }

    public List<RelicSetVO> getRelicSets() {
        return relicSets;
    }

    public void setRelicSets(List<RelicSetVO> relicSets) {
        this.relicSets = relicSets;
    }

    public List<SubStatVO> getSubStats() {
        return subStats;
    }

    public void setSubStats(List<SubStatVO> subStats) {
        this.subStats = subStats;
    }
}
