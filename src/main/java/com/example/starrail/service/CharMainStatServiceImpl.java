package com.example.starrail.service;

import com.example.starrail.dao.CharMainStatMapper;
import com.example.starrail.po.CharMainStat;
import com.example.starrail.po.CharMainStatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharMainStatServiceImpl implements CharMainStatService{

    @Autowired
    CharMainStatMapper charMainStatMapper;

    @Override
    public List<CharMainStat> getMainStatByChar(Integer characterId) {
        CharMainStatExample example = new CharMainStatExample();
        example.createCriteria().andCharacterIdEqualTo(characterId);
        return charMainStatMapper.selectByExample(example);
    }

    @Override
    public List<CharMainStat> getMainStatByCharAndType(Integer characterId, Integer relicTypeId) {
        CharMainStatExample example = new CharMainStatExample();
        example.createCriteria().andCharacterIdEqualTo(characterId).andRelicTypeIdEqualTo(relicTypeId);
        return charMainStatMapper.selectByExample(example);
    }

    @Override
    public Boolean addCharMainStat(CharMainStat charMainStat) {
        return charMainStatMapper.insert(charMainStat) > 0;
    }

    @Override
    public Boolean addCharMainStatList(List<CharMainStat> charMainStatList) {
        for(CharMainStat charMainStat : charMainStatList) {
            charMainStatMapper.insert(charMainStat);
        }
        return true;
    }

    @Override
    public Boolean delCharMainStat(CharMainStat charMainStat) {
        if(charMainStat.getCharMainStatId() != null) {
            return charMainStatMapper.deleteByPrimaryKey(charMainStat.getCharMainStatId()) > 0;
        } else {
            CharMainStatExample example = new CharMainStatExample();
            example.createCriteria().andCharacterIdEqualTo(charMainStat.getCharMainStatId()).
                    andRelicTypeIdEqualTo(charMainStat.getRelicTypeId()).
                    andCharMainStatIdEqualTo(charMainStat.getCharMainStatId());
            return charMainStatMapper.deleteByExample(example) > 0;
        }
    }

    @Override
    public Boolean delCharMainStatList(List<CharMainStat> charMainStatList) {
        for(CharMainStat charMainStat : charMainStatList) {
            delCharMainStat(charMainStat);
        }
        return true;
    }


}
