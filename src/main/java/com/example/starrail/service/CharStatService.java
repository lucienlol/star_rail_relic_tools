package com.example.starrail.service;

import com.example.starrail.po.CharRelicSet;
import com.example.starrail.po.CharStat;

import java.util.List;

public interface CharStatService {

    public List<CharStat> getCharStatByIdAndPriority(int characterId, int priority);

    public List<CharStat> getCharStatById(int characterId);

    public boolean addCharStat(CharStat charStat);

    public boolean addCharStatList(List<CharStat> charStatList);

    public boolean delCharStat(CharStat charStat);

    public boolean delCharStatList(List<CharStat> charStatList);
}
