package com.example.starrail.service;

import com.example.starrail.po.CharMainStat;

import java.util.List;

public interface CharMainStatService {

    public List<CharMainStat> getMainStatByChar(Integer characterId);

    public List<CharMainStat> getMainStatByCharAndType(Integer characterId, Integer relicTypeId);

    public Boolean addCharMainStat(CharMainStat charMainStat);

    public Boolean addCharMainStatList(List<CharMainStat> charMainStatList);
    public Boolean delCharMainStat(CharMainStat charMainStat);

    public Boolean delCharMainStatList(List<CharMainStat> charMainStatList);
}
