package com.example.starrail.service;

import com.example.starrail.po.CharMainStat;
import com.example.starrail.po.CharRelicSet;
import com.example.starrail.po.CharStat;
import com.example.starrail.po.RelicEntity;
import com.example.starrail.vo.MainStatVO;
import com.example.starrail.vo.RelicEntityVO;
import com.example.starrail.vo.RelicSetVO;
import com.example.starrail.vo.SubStatVO;

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

    public RelicEntity toPO(RelicEntityVO relicEntityVO);

}
