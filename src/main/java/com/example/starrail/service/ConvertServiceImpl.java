package com.example.starrail.service;

import com.example.starrail.po.*;
import com.example.starrail.vo.MainStatVO;
import com.example.starrail.vo.RelicSetVO;
import com.example.starrail.vo.SubStatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
