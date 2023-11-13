package com.example.starrail.service;

import com.example.starrail.po.RelicSet;
import com.example.starrail.po.RelicType;
import com.example.starrail.po.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CacheServiceImpl implements CacheService{

    HashMap<String, RelicSet> relicSetNameMap = new HashMap<>();

    HashMap<Integer, RelicSet> relicSetIdMap = new HashMap<>();

    HashMap<String, Stat> statNameMap = new HashMap<>();

    HashMap<Integer, Stat> statIdMap = new HashMap<>();

    List<RelicSet> relicSetLists = new ArrayList<>();

    List<RelicSet> cavernRelicLists = new ArrayList<>();

    List<RelicSet> planarOrnamentLists = new ArrayList<>();

    List<Stat> statList = new ArrayList<>();

    List<Stat> bodyStatList = new ArrayList<>();

    List<Stat> feetStatList = new ArrayList<>();

    List<Stat> sphereStatList = new ArrayList<>();

    List<Stat> ropeStatList = new ArrayList<>();

    List<RelicType> relicTypeList = new ArrayList<>();

    HashMap<String, RelicType> relicTypeNameMap = new HashMap<>();

    HashMap<Integer, RelicType> relicTypeIdMap = new HashMap<>();

    @Autowired
    CharacterService characterService;

    @Autowired
    CharMainStatService charMainStatService;

    @Autowired
    CharRelicSetService charRelicSetService;

    @Autowired
    CharStatService charStatService;

    @Autowired
    RelicSetService relicSetService;

    @Autowired
    RelicTypeService relicTypeService;

    @Autowired
    StatService statService;

    @Override
    public void refresh() {

    }

    @Override
    public RelicSet getRelicSetByName(String relicSetName) {
        if(relicSetNameMap.isEmpty()) {
            refreshRelicSet();
        }
        return relicSetNameMap.get(relicSetName);
    }

    @Override
    public RelicSet getRelicSetById(Integer relicSetId) {
        if(relicSetIdMap.isEmpty()) {
            refreshRelicSet();
        }
        return relicSetIdMap.get(relicSetId);
    }

    @Override
    public Stat getStatByName(String statName) {
        if(statNameMap.isEmpty()) {
            refreshStat();
        }
        return statNameMap.get(statName);
    }

    @Override
    public Stat getStatById(Integer statId) {
        if(statIdMap.isEmpty()) {
            refreshStat();
        }
        return statIdMap.get(statId);
    }

    @Override
    public RelicType getRelicTypeByName(String relicTypeName) {
        if(relicTypeNameMap.isEmpty()) {
            refreshRelicType();
        }
        return relicTypeNameMap.get(relicTypeName);
    }

    @Override
    public RelicType getRelicTypeById(Integer relicTypeId) {
        if(relicTypeIdMap.isEmpty()) {
            refreshRelicType();
        }
        return relicTypeIdMap.get(relicTypeId);
    }


    private void refreshRelicSet() {
        System.out.println("begin relic refresh");
        relicSetLists = relicSetService.getAllRelicSets();
        cavernRelicLists = relicSetService.getAllCavernRelics();
        planarOrnamentLists = relicSetService.getAllPlanarOrnaments();

        for(RelicSet relicSet : relicSetLists) {
            relicSetNameMap.put(relicSet.getRelicSetName(), relicSet);
            relicSetIdMap.put(relicSet.getRelicSetId(), relicSet);
        }
    }

    private void refreshStat() {
        System.out.println("begin stat refresh");
        statList = statService.getAllStats();

        for(Stat stat : statList) {
            statNameMap.put(stat.getStatName(), stat);
            statIdMap.put(stat.getStatId(), stat);
        }

        bodyStatList = statService.getAllBodyStats();
        feetStatList = statService.getAllFeetStats();
        sphereStatList = statService.getAllSphereStats();
        ropeStatList = statService.getAllRopeStats();
    }

    private void refreshRelicType() {
        System.out.println("begin type refresh");
        relicTypeList = relicTypeService.getAllRelicType();

        for(RelicType relicType : relicTypeList) {
            relicTypeIdMap.put(relicType.getRelicTypeId(), relicType);
            relicTypeNameMap.put(relicType.getRelicTypeName(), relicType);
        }

    }
}
