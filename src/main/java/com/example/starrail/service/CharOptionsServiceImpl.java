package com.example.starrail.service;

import com.example.starrail.dao.CharOptionsMapper;
import com.example.starrail.po.CharOptions;
import com.example.starrail.po.CharOptionsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharOptionsServiceImpl implements CharOptionsService{

    @Autowired
    CharOptionsMapper charOptionsMapper;

    @Override
    public CharOptions getById(Integer characterId) {
        CharOptionsExample example = new CharOptionsExample();
        example.createCriteria().andCharacterIdEqualTo(characterId);
        return charOptionsMapper.selectByExample(example).get(0);
    }

    @Override
    public List<CharOptions> getAll() {
        CharOptionsExample example = new CharOptionsExample();
        return charOptionsMapper.selectByExample(example);
    }

    @Override
    public Boolean addCharOptions(CharOptions charOptions) {
        return charOptionsMapper.insert(charOptions) > 0;
    }

    @Override
    public Boolean updateCharOptions(CharOptions charOptions) {
        CharOptionsExample example = new CharOptionsExample();
        example.createCriteria().andCharacterIdEqualTo(charOptions.getCharacterId());
        return charOptionsMapper.updateByExampleSelective(charOptions, example) > 0;
    }
}
