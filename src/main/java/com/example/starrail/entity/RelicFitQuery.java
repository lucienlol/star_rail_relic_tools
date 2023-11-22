package com.example.starrail.entity;

import java.util.List;

public class RelicFitQuery {
    List<Integer> relicFitIdList;

    List<Integer> relicIdList;

    List<Integer> characterIdList;

    List<Integer> relicLevelList;

    List<Integer> relicTypeList;

    List<Integer> relicSetIdList;

    List<Integer> mainStatIdList;

    Boolean isMainStatFit;

    Boolean isRelicSetFit;

    public List<Integer> getRelicLevelList() {
        return relicLevelList;
    }

    public void setRelicLevelList(List<Integer> relicLevelList) {
        this.relicLevelList = relicLevelList;
    }

    public List<Integer> getRelicTypeList() {
        return relicTypeList;
    }

    public void setRelicTypeList(List<Integer> relicTypeList) {
        this.relicTypeList = relicTypeList;
    }

    public List<Integer> getRelicFitIdList() {
        return relicFitIdList;
    }

    public void setRelicFitIdList(List<Integer> relicFitIdList) {
        this.relicFitIdList = relicFitIdList;
    }

    public List<Integer> getRelicIdList() {
        return relicIdList;
    }

    public void setRelicIdList(List<Integer> relicIdList) {
        this.relicIdList = relicIdList;
    }

    public List<Integer> getCharacterIdList() {
        return characterIdList;
    }

    public void setCharacterIdList(List<Integer> characterIdList) {
        this.characterIdList = characterIdList;
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

    public List<Integer> getRelicSetIdList() {
        return relicSetIdList;
    }

    public void setRelicSetIdList(List<Integer> relicSetIdList) {
        this.relicSetIdList = relicSetIdList;
    }

    public List<Integer> getMainStatIdList() {
        return mainStatIdList;
    }

    public void setMainStatIdList(List<Integer> mainStatIdList) {
        this.mainStatIdList = mainStatIdList;
    }
}
