package com.example.starrail.service;

import com.example.starrail.po.CharOptions;

import java.util.List;

public interface CharOptionsService {

    CharOptions getById(Integer characterId);

    List<CharOptions> getAll();

    Boolean addCharOptions(CharOptions charOptions);

    Boolean updateCharOptions(CharOptions charOptions);
}
