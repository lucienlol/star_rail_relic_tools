package com.example.starrail.service;

import com.example.starrail.po.CharBuildSug;

public interface CharBuildSugService {

    CharBuildSug getCharBuildSug(Integer characterId);

    public Boolean addCharBuildSug(CharBuildSug charBuildSug);

    public Boolean updateCharBuildSug(CharBuildSug charBuildSug);
}
