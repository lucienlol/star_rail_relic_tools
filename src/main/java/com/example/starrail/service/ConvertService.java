package com.example.starrail.service;

import com.example.starrail.entity.RelicFitDetail;
import com.example.starrail.entity.RelicFitQuery;
import com.example.starrail.po.*;
import com.example.starrail.vo.*;

import java.util.List;

public interface ConvertService {

    public MainStatVO toVO(CharMainStat charMainStat);

    public CharMainStat toPO(MainStatVO mainStatVO);

    public CharMainStat toPO(MainStatVO mainStatVO, Integer characterId);

    public RelicSetVO toVO(CharRelicSet charRelicSet);

    public CharRelicSet toPO(RelicSetVO relicSetVO);

    public CharRelicSet toPO(RelicSetVO relicSetVO, Integer characterId);

    public SubStatVO toVO(CharStat charStat);

    public CharStat toPO(SubStatVO subStatVO);

    public CharStat toPO(SubStatVO subStatVO, Integer characterId);

    public RelicEntityVO toVO(RelicEntity relicEntity);

    public RelicEntity toPO(RelicEntityVO relicEntityVO);

    public RelicEntity toPO(RelicEntityVO relicEntityVO, Integer relicEntityId);

    public List<StarRailCharacter> toCharacterList(List<String> characters);

    public List<RelicEntity> toRelicList(List<String> relics);

    public List<Integer> toIntList(List<String> strList);

    public RelicFitQuery toQuery(RelicFitReq relicFitReq);

    public RelicFitDetailVO toVO(RelicFitDetail relicFitDetail);

}
