package com.example.starrail.service;

import com.example.starrail.vo.CharacterBuild;

public interface CharacterBuildService {

    Boolean addCharacterBuild(CharacterBuild characterBuild);

    Boolean updateCharacterBuild(CharacterBuild characterBuild);

    CharacterBuild getCharacterBuild(Integer characterId);
}
