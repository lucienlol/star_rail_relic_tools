package com.example.starrail.service;

import com.example.starrail.dao.RelicEntityMapper;
import com.example.starrail.po.RelicEntity;
import com.example.starrail.po.RelicEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelicEntityServiceImpl implements RelicEntityService{

    @Autowired
    RelicEntityMapper mapper;

    @Override
    public Boolean insertRelicEntity(RelicEntity entity) {
        return mapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateRelicEntity(RelicEntity entity) {
        RelicEntityExample example = new RelicEntityExample();
        example.createCriteria().andRelicEntityIdEqualTo(entity.getRelicEntityId());
        return mapper.updateByExampleSelective(entity, example) > 0;
    }

    @Override
    public RelicEntity getById(Integer relicEntityId) {
        return mapper.selectByPrimaryKey(relicEntityId);
    }

    @Override
    public List<RelicEntity> getAll() {
        RelicEntityExample example = new RelicEntityExample();
        return mapper.selectByExample(example);
    }

    @Override
    public Boolean deleteRelicEntity(Integer id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }
}
