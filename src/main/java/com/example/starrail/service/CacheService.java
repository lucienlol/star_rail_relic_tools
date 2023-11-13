package com.example.starrail.service;

import com.example.starrail.po.RelicSet;
import com.example.starrail.po.RelicType;
import com.example.starrail.po.Stat;

public interface CacheService {

    void refresh();

    RelicSet getRelicSetByName(String relicSetName);

    RelicSet getRelicSetById(Integer relicSetId);

    Stat getStatByName(String statName);

    Stat getStatById(Integer statId);

    RelicType getRelicTypeByName(String relicTypeName);

    RelicType getRelicTypeById(Integer relicTypeId);

}
