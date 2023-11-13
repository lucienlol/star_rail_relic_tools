package com.example.starrail.service;

import com.example.starrail.po.RelicSet;

import java.util.List;

public interface RelicSetService {

    Boolean addRelicSet(RelicSet relicSet);

    Boolean addRelicSets(List<RelicSet> relicSets);

    List<RelicSet> getAllRelicSets();

    List<RelicSet> getAllCavernRelics();

    List<RelicSet> getAllPlanarOrnaments();
}
