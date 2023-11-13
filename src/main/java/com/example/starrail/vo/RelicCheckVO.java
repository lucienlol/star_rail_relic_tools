package com.example.starrail.vo;

import java.util.List;

public class RelicCheckVO {

    Integer relicLevel;

    List<String> relicSetList;

    List<String> relicTypeList;

    List<String> mainStatList;

    List<StatValueVO> subStatList;

    public List<String> getMainStatList() {
        return mainStatList;
    }

    public void setMainStatList(List<String> mainStatList) {
        this.mainStatList = mainStatList;
    }

    public List<StatValueVO> getSubStatList() {
        return subStatList;
    }

    public void setSubStatList(List<StatValueVO> subStatList) {
        this.subStatList = subStatList;
    }

    public Integer getRelicLevel() {
        return relicLevel;
    }

    public void setRelicLevel(Integer relicLevel) {
        this.relicLevel = relicLevel;
    }

    public List<String> getRelicSetList() {
        return relicSetList;
    }

    public void setRelicSetList(List<String> relicSetList) {
        this.relicSetList = relicSetList;
    }

    public List<String> getRelicTypeList() {
        return relicTypeList;
    }

    public void setRelicTypeList(List<String> relicTypeList) {
        this.relicTypeList = relicTypeList;
    }
}
