package com.example.starrail.service;

import com.example.starrail.dao.CharRelicSetMapper;
import com.example.starrail.po.CharRelicSet;
import com.example.starrail.po.CharRelicSetExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharRelicSetServiceImpl implements CharRelicSetService{

    @Autowired
    CharRelicSetMapper charRelicSetMapper;

    @Override
    public List<CharRelicSet> getCharRelicSetById(Integer characterId) {
        CharRelicSetExample example = new CharRelicSetExample();
        example.createCriteria().andCharacterIdEqualTo(characterId);
        return charRelicSetMapper.selectByExample(example);
    }

    @Override
    public List<CharRelicSet> getHalfCavernRelicsById(Integer characterId) {
        CharRelicSetExample example = new CharRelicSetExample();
        example.createCriteria().andCharacterIdEqualTo(characterId).andRelicSetTypeEqualTo(ConstUtil.CAVERN_RELICS)
                .andEffectDemandEqualTo(ConstUtil.HALF_SET_DEMAND);
        return charRelicSetMapper.selectByExample(example);
    }

    @Override
    public List<CharRelicSet> getAllCavernRelicById(Integer characterId) {
        CharRelicSetExample example = new CharRelicSetExample();
        example.createCriteria().andCharacterIdEqualTo(characterId).andRelicSetTypeEqualTo(ConstUtil.CAVERN_RELICS)
                .andEffectDemandEqualTo(ConstUtil.ALL_SET_DEMAND);
        return charRelicSetMapper.selectByExample(example);
    }

    @Override
    public List<CharRelicSet> getPlanarOrnamentsById(Integer characterId) {
        CharRelicSetExample example = new CharRelicSetExample();
        example.createCriteria().andCharacterIdEqualTo(characterId).andRelicSetTypeEqualTo(ConstUtil.PLANAR_ORNAMENTS)
                .andEffectDemandEqualTo(ConstUtil.ALL_SET_DEMAND);
        return charRelicSetMapper.selectByExample(example);
    }

    @Override
    public Boolean delCharRelicSet(CharRelicSet charRelicSet) {
        if(charRelicSet.getCharRelicSetId() != null) {
            return charRelicSetMapper.deleteByPrimaryKey(charRelicSet.getCharRelicSetId()) > 0;
        } else {
            CharRelicSetExample example = new CharRelicSetExample();
            example.createCriteria().andCharacterIdEqualTo(charRelicSet.getCharacterId())
                    .andRelicSetTypeEqualTo(charRelicSet.getRelicSetType())
                    .andEffectDemandEqualTo(charRelicSet.getEffectDemand());
            return charRelicSetMapper.deleteByExample(example) > 0;
        }
    }

    @Override
    public Boolean delCharRelicSetList(List<CharRelicSet> charRelicSetList) {
        for(CharRelicSet charRelicSet : charRelicSetList) {
            delCharRelicSet(charRelicSet);
        }
        return true;
    }

    @Override
    public Boolean addCharRelicSet(CharRelicSet charRelicSet) {
        return charRelicSetMapper.insert(charRelicSet) > 0;
    }

    @Override
    public Boolean addCharRelicSetList(List<CharRelicSet> charRelicSetList) {
        for(CharRelicSet charRelicSet : charRelicSetList) {
            addCharRelicSet(charRelicSet);
        }
        return true;
    }
}
