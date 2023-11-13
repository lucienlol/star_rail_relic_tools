package com.example.starrail.service;

import com.example.starrail.vo.CharacterCheckVO;
import com.example.starrail.vo.RelicCheckVO;

import java.util.List;

public interface RelicCheckService {

    void prepare();

    List<CharacterCheckVO> doCheck(RelicCheckVO relicCheckVO);
}
