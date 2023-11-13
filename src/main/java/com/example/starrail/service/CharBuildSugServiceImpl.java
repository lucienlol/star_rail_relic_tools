package com.example.starrail.service;

import com.example.starrail.dao.CharBuildSugMapper;
import com.example.starrail.po.CharBuildSug;
import com.example.starrail.po.CharBuildSugExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharBuildSugServiceImpl implements CharBuildSugService {

    @Autowired
    CharBuildSugMapper mapper;

    @Override
    public CharBuildSug getCharBuildSug(Integer characterId) {
        CharBuildSugExample example = new CharBuildSugExample();
        example.createCriteria().andCharacterIdEqualTo(characterId);
        return mapper.selectByExampleWithBLOBs(example).get(0);
    }

    @Override
    public Boolean addCharBuildSug(CharBuildSug charBuildSug) {
        return mapper.insert(charBuildSug) > 0;
    }

    @Override
    public Boolean updateCharBuildSug(CharBuildSug charBuildSug) {
        CharBuildSugExample example = new CharBuildSugExample();
        example.createCriteria().andCharacterIdEqualTo(charBuildSug.getCharacterId());
        return mapper.updateByExampleSelective(charBuildSug, example) > 0;
    }
}
