package com.example.starrail.service;

import com.example.starrail.po.RelicEntity;
import com.example.starrail.po.RelicFit;
import com.example.starrail.po.StarRailCharacter;
import com.example.starrail.vo.CharacterCheckVO;
import com.example.starrail.vo.RelicEntityVO;

import java.util.List;

public interface RelicCheckService {

    void prepare();

    List<CharacterCheckVO> doCheck(RelicEntityVO relicEntityVO);

    List<RelicFit> genRelicFit(List<StarRailCharacter> characterList, List<RelicEntity> relicList);
}
