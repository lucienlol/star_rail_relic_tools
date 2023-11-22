package com.example.starrail.service;

import com.example.starrail.dao.StarRailCharacterMapper;
import com.example.starrail.po.CharRelicSet;
import com.example.starrail.po.RelicSet;
import com.example.starrail.po.StarRailCharacter;
import com.example.starrail.po.StarRailCharacterExample;
import com.example.starrail.vo.CharacterBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService{

    @Autowired
    StarRailCharacterMapper mapper;

    @Override
    public StarRailCharacter getById(Integer characterId) {
        return mapper.selectByPrimaryKey(characterId);
    }

    @Override
    public List<StarRailCharacter> getAll() {
        StarRailCharacterExample example = new StarRailCharacterExample();
        return mapper.selectByExample(example);
    }

    @Override
    public List<StarRailCharacter> getAllShow() {
        StarRailCharacterExample example = new StarRailCharacterExample();
        example.createCriteria().andIsHideEqualTo(false);
        return mapper.selectByExample(example);
    }

    @Override
    public Boolean addCharacter(StarRailCharacter character) {
        int insertColumn = mapper.insert(character);
        return insertColumn > 0;
    }

    @Override
    public Boolean updateCharacter(StarRailCharacter character) {
        StarRailCharacterExample example = new StarRailCharacterExample();
        example.createCriteria().andCharacterIdEqualTo(character.getCharacterId());
        int updateColumn = mapper.updateByExample(character, example);
        return updateColumn > 0;
    }

    @Override
    public List<StarRailCharacter> getList(List<Integer> idList) {
        StarRailCharacterExample example = new StarRailCharacterExample();
        example.createCriteria().andCharacterIdIn(idList);
        return mapper.selectByExample(example);
    }

}
