package com.example.starrail.service;

import com.example.starrail.po.CharRelicSet;

import java.util.List;

public interface CharRelicSetService {

    public List<CharRelicSet> getCharRelicSetById(Integer characterId);

    public List<CharRelicSet> getHalfCavernRelicsById(Integer characterId);

    public List<CharRelicSet> getAllCavernRelicById(Integer characterId);

    public List<CharRelicSet> getPlanarOrnamentsById(Integer characterId);

    public Boolean delCharRelicSet(CharRelicSet charRelicSet);

    public Boolean delCharRelicSetList(List<CharRelicSet> charRelicSetList);

    public Boolean addCharRelicSet(CharRelicSet charRelicSet);

    public Boolean addCharRelicSetList(List<CharRelicSet> charRelicSetList);
}
