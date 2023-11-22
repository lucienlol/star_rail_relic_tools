package com.example.starrail.service;

import com.example.starrail.po.StarRailCharacter;
import com.example.starrail.vo.CharacterBuild;

import java.util.List;

public interface CharacterService {

    StarRailCharacter getById(Integer characterId);

    List<StarRailCharacter> getAll();

    List<StarRailCharacter> getAllShow();

    Boolean addCharacter(StarRailCharacter character);

    Boolean updateCharacter(StarRailCharacter character);

    List<StarRailCharacter> getList(List<Integer> idList);
}
