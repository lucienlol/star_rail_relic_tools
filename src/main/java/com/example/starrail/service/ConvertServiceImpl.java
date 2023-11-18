package com.example.starrail.service;

import com.example.starrail.po.*;
import com.example.starrail.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ConvertServiceImpl implements ConvertService{

    @Autowired
    CacheService cacheService;

    @Override
    public MainStatVO toVO(CharMainStat charMainStat) {
        Stat stat = cacheService.getStatById(charMainStat.getStatId());
        MainStatVO vo = new MainStatVO();
        vo.setStatName(stat.getStatName());
        vo.setRelicType(charMainStat.getRelicTypeId());
        return vo;
    }

    @Override
    public CharMainStat toPO(MainStatVO mainStatVO) {
        CharMainStat charMainStat = new CharMainStat();
        Stat stat = cacheService.getStatByName(mainStatVO.getStatName());
        charMainStat.setStatId(stat.getStatId());
        charMainStat.setRelicTypeId(mainStatVO.getRelicType());
        return charMainStat;
    }

    @Override
    public CharMainStat toPO(MainStatVO mainStatVO, Integer characterId) {
        CharMainStat charMainStat = toPO(mainStatVO);
        charMainStat.setCharacterId(characterId);
        return charMainStat;
    }

    @Override
    public RelicSetVO toVO(CharRelicSet charRelicSet) {
        RelicSet relicSet = cacheService.getRelicSetById(charRelicSet.getRelicSetId());
        RelicSetVO vo = new RelicSetVO();
        vo.setEffectDemand(charRelicSet.getEffectDemand());
        vo.setRelicSetType(relicSet.getRelicSetType());
        vo.setRelicSetName(relicSet.getRelicSetName());
        return vo;
    }

    @Override
    public CharRelicSet toPO(RelicSetVO relicSetVO) {
        RelicSet relicSet = cacheService.getRelicSetByName(relicSetVO.getRelicSetName());
        CharRelicSet charRelicSet = new CharRelicSet();
        charRelicSet.setEffectDemand(relicSetVO.getEffectDemand());
        charRelicSet.setRelicSetId(relicSet.getRelicSetId());
        charRelicSet.setRelicSetType(relicSet.getRelicSetType());
        return charRelicSet;
    }

    @Override
    public CharRelicSet toPO(RelicSetVO relicSetVO, Integer characterId) {
        CharRelicSet charRelicSet = toPO(relicSetVO);
        charRelicSet.setCharacterId(characterId);
        return charRelicSet;
    }

    @Override
    public SubStatVO toVO(CharStat charStat) {
        Stat stat = cacheService.getStatById(charStat.getStatId());
        SubStatVO vo = new SubStatVO();
        vo.setStatName(stat.getStatName());
        vo.setPriority(charStat.getPriority());
        return vo;
    }

    @Override
    public CharStat toPO(SubStatVO subStatVO) {
        CharStat charStat = new CharStat();
        Stat stat = cacheService.getStatByName(subStatVO.getStatName());
        charStat.setStatId(stat.getStatId());
        charStat.setPriority(subStatVO.getPriority());
        return charStat;
    }

    @Override
    public CharStat toPO(SubStatVO subStatVO, Integer characterId) {
        CharStat charStat = toPO(subStatVO);
        charStat.setCharacterId(characterId);
        return charStat;
    }

    @Override
    public RelicEntityVO toVO(RelicEntity relicEntity) {
        RelicEntityVO vo = new RelicEntityVO();
        vo.setRelicId(relicEntity.getRelicEntityId());
        vo.setRelicLevel(relicEntity.getRelicLevel());
        vo.setRelicSetList(Collections.singletonList(cacheService.getRelicSetById(relicEntity.getRelicSetId()).getRelicSetName()));
        vo.setRelicTypeList(Collections.singletonList(cacheService.getRelicTypeById(relicEntity.getRelicTypeId()).getRelicTypeName()));
        vo.setMainStatList(Collections.singletonList(cacheService.getStatById(relicEntity.getMainStatId()).getStatName()));

        List<StatValueVO> statValueVOList = new ArrayList<>();
        String[] subStats = relicEntity.getSubStatValues().split("\n");
        for(String subStat : subStats) {
            StatValueVO statValueVO = new StatValueVO();
            statValueVO.setStatName(subStat.split(":")[0]);
            statValueVO.setValue(subStat.split(":")[1]);
            statValueVOList.add(statValueVO);
        }
        vo.setSubStatList(statValueVOList);
        return vo;
    }

    @Override
    public RelicEntity toPO(RelicEntityVO relicEntityVO) {
        RelicEntity relicEntity = new RelicEntity();
        relicEntity.setRelicEntityId(relicEntityVO.getRelicId());
        relicEntity.setRelicLevel(relicEntityVO.getRelicLevel());
        relicEntity.setRelicSetId(cacheService.getRelicSetByName(relicEntityVO.getRelicSetList().get(0)).getRelicSetId());
        relicEntity.setRelicTypeId(cacheService.getRelicTypeByName(relicEntityVO.getRelicTypeList().get(0)).getRelicTypeId());
        relicEntity.setMainStatId(cacheService.getStatByName(relicEntityVO.getMainStatList().get(0)).getStatId());

        StringBuilder sb = new StringBuilder("");
        for(StatValueVO statValueVO : relicEntityVO.getSubStatList()) {
            sb.append(statValueVO.getStatName()).append(":").append(statValueVO.getValue()).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        relicEntity.setSubStatValues(sb.toString());

        return relicEntity;
    }

    @Override
    public RelicEntity toPO(RelicEntityVO relicEntityVO, Integer relicEntityId) {
        RelicEntity relicEntity = toPO(relicEntityVO);
        relicEntity.setRelicEntityId(relicEntityId);
        return relicEntity;
    }
}
