package com.example.starrail.service;

import com.example.starrail.dao.RelicTypeMapper;
import com.example.starrail.po.RelicType;
import com.example.starrail.po.RelicTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelicTypeServiceImpl implements RelicTypeService{

    @Autowired
    RelicTypeMapper mapper;

    @Override
    public List<RelicType> getAllRelicType() {
        RelicTypeExample example = new RelicTypeExample();
        return mapper.selectByExample(example);
    }
}
