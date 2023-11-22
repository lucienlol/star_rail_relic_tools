package com.example.starrail.service;

import com.example.starrail.dao.RelicFitMapper;
import com.example.starrail.entity.RelicFitDetail;
import com.example.starrail.entity.RelicFitQuery;
import com.example.starrail.po.RelicFit;
import com.example.starrail.po.RelicFitExample;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelicFitServiceImpl implements RelicFitService {

    @Autowired
    RelicFitMapper mapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public Boolean addRelicFit(RelicFit relicFit) {
        return mapper.insert(relicFit) > 0;
    }

    @Override
    public Boolean addRelicFitList(List<RelicFit> relicFitList) {
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        RelicFitMapper relicFitMapper = session.getMapper(RelicFitMapper.class);
        for(RelicFit relicFit : relicFitList) {
            relicFitMapper.insert(relicFit);
        }
        session.commit();
        session.close();
        return true;
    }

    @Override
    public Boolean deleteRelicFitByQuery(RelicFitQuery relicFitQuery) {
        RelicFitExample example = new RelicFitExample();
        RelicFitExample.Criteria criteria = example.createCriteria();
        if(relicFitQuery.getRelicFitIdList() != null) {
            criteria.andRelicFitIdIn(relicFitQuery.getRelicFitIdList());
        }
        if(relicFitQuery.getCharacterIdList() != null) {
            criteria.andCharacterIdIn(relicFitQuery.getCharacterIdList());
        }
        if(relicFitQuery.getRelicIdList() != null) {
            criteria.andRelicIdIn(relicFitQuery.getRelicIdList());
        }
        if(relicFitQuery.getRelicSetFit() != null) {
            criteria.andIsRelicSetFitEqualTo(relicFitQuery.getRelicSetFit());
        }
        if(relicFitQuery.getMainStatFit() != null) {
            criteria.andIsMainStatFitEqualTo(relicFitQuery.getMainStatFit());
        }
        return mapper.deleteByExample(example) > 0;
    }

    @Override
    public List<RelicFitDetail> getDetailByQuery(RelicFitQuery query) {
        return mapper.getDetailByQuery(query);
    }


}
