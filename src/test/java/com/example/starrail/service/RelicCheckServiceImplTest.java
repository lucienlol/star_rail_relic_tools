package com.example.starrail.service;

import com.example.starrail.po.RelicEntity;
import com.example.starrail.po.RelicFit;
import com.example.starrail.po.StarRailCharacter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RelicCheckServiceImplTest {

    @Autowired
    CacheService cacheService;

    @Autowired
    CharacterService characterService;

    @Autowired
    RelicEntityService relicEntityService;

    @Autowired
    RelicFitService relicFitService;

    @Autowired
    RelicCheckService relicCheckService;

    @Test
    void genRelicFit() {
        List<Integer> charIdList = IntStream.range(1, 17).boxed().toList();
        List<Integer> relicIdList = IntStream.range(1, 6).boxed().toList();
        List<StarRailCharacter> characterList = characterService.getList(charIdList);
        List<RelicEntity> relicEntityList = relicEntityService.getRelicList(relicIdList);
        List<RelicFit> relicFitList = relicCheckService.genRelicFit(characterList, relicEntityList);
    }
}