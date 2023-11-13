package com.example.starrail.service;

import com.example.starrail.dao.CharStatMapper;
import com.example.starrail.po.CharStat;
import com.example.starrail.po.CharStatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharStatServiceImpl implements CharStatService{

    @Autowired
    CharStatMapper mapper;


    @Override
    public List<CharStat> getCharStatByIdAndPriority(int characterId, int priority) {
        CharStatExample example = new CharStatExample();
        example.createCriteria().andCharacterIdEqualTo(characterId).andPriorityEqualTo(priority);
        return mapper.selectByExample(example);
    }

    @Override
    public List<CharStat> getCharStatById(int characterId) {
        CharStatExample example = new CharStatExample();
        example.createCriteria().andCharacterIdEqualTo(characterId);
        return mapper.selectByExample(example);
    }

    @Override
    public boolean addCharStat(CharStat charStat) {
        return mapper.insert(charStat) > 0;
    }

    @Override
    public boolean addCharStatList(List<CharStat> charStatList) {
        for(CharStat charStat : charStatList) {
            addCharStat(charStat);
        }
        return true;
    }

    @Override
    public boolean delCharStat(CharStat charStat) {
        if(charStat.getCharStatId() != null) {
            return mapper.deleteByPrimaryKey(charStat.getCharStatId()) > 0;
        } else {
            CharStatExample example = new CharStatExample();
            example.createCriteria().andCharacterIdEqualTo(charStat.getCharStatId())
                    .andStatIdEqualTo(charStat.getStatId())
                    .andPriorityEqualTo(charStat.getPriority());
            return mapper.deleteByExample(example) > 0;
        }
    }

    @Override
    public boolean delCharStatList(List<CharStat> charStatList) {
        for(CharStat charStat : charStatList) {
            delCharStat(charStat);
        }
        return true;
    }
}
