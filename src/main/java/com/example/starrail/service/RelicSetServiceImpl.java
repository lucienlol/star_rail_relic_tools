package com.example.starrail.service;

import com.example.starrail.dao.RelicSetMapper;
import com.example.starrail.po.RelicSet;
import com.example.starrail.po.RelicSetExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelicSetServiceImpl implements RelicSetService{

    @Autowired
    RelicSetMapper mapper;

    @Override
    public Boolean addRelicSet(RelicSet relicSet) {
        int insertColumns = mapper.insert(relicSet);
        return insertColumns > 0;
    }

    @Override
    public Boolean addRelicSets(List<RelicSet> relicSets) {
        for(RelicSet relicSet: relicSets) {
            addRelicSet(relicSet);
        };
        return true;
    }

    @Override
    public List<RelicSet> getAllRelicSets() {
        RelicSetExample example = new RelicSetExample();
        return mapper.selectByExample(example);
    }

    @Override
    public List<RelicSet> getAllCavernRelics() {
        RelicSetExample example = new RelicSetExample();
        example.createCriteria().andRelicSetTypeEqualTo(1);
        return mapper.selectByExample(example);
    }

    @Override
    public List<RelicSet> getAllPlanarOrnaments() {
        RelicSetExample example = new RelicSetExample();
        example.createCriteria().andRelicSetTypeEqualTo(2);
        return mapper.selectByExample(example);
    }
}
