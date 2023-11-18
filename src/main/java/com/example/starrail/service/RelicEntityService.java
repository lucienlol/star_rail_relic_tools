package com.example.starrail.service;

import com.example.starrail.po.RelicEntity;

import java.util.List;


public interface RelicEntityService {
    public Boolean insertRelicEntity(RelicEntity entity);

    public Boolean updateRelicEntity(RelicEntity entity);

    public RelicEntity getById(Integer relicEntityId);

    public List<RelicEntity> getAll();

    public Boolean deleteRelicEntity(Integer id);
}
