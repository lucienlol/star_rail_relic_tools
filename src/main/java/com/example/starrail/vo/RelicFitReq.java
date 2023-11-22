package com.example.starrail.vo;

import java.util.List;

public class RelicFitReq {


    List<String> characters;

    List<String> relics;

    List<String> relicLevels;

    List<String> relicTypes;

    List<String> relicSets;

    List<String> mainStats;

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getRelics() {
        return relics;
    }

    public void setRelics(List<String> relics) {
        this.relics = relics;
    }

    public List<String> getRelicSets() {
        return relicSets;
    }

    public void setRelicSets(List<String> relicSets) {
        this.relicSets = relicSets;
    }

    public List<String> getMainStats() {
        return mainStats;
    }

    public void setMainStats(List<String> mainStats) {
        this.mainStats = mainStats;
    }
    public List<String> getRelicLevels() {
        return relicLevels;
    }

    public void setRelicLevels(List<String> relicLevels) {
        this.relicLevels = relicLevels;
    }

    public List<String> getRelicTypes() {
        return relicTypes;
    }

    public void setRelicTypes(List<String> relicTypes) {
        this.relicTypes = relicTypes;
    }

}
