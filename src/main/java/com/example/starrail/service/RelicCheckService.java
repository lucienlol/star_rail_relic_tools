package com.example.starrail.service;

import com.example.starrail.vo.CharacterCheckVO;
import com.example.starrail.vo.RelicEntityVO;

import java.util.List;

public interface RelicCheckService {

    void prepare();

    List<CharacterCheckVO> doCheck(RelicEntityVO relicEntityVO);
}
