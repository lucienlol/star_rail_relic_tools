package com.example.starrail.service;

import com.example.starrail.po.*;
import com.example.starrail.vo.CharacterBuild;
import com.example.starrail.vo.MainStatVO;
import com.example.starrail.vo.RelicSetVO;
import com.example.starrail.vo.SubStatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CharacterBuildServiceImpl implements CharacterBuildService{

    @Autowired
    CacheService cacheService;

    @Autowired
    ConvertService convertService;

    @Autowired
    CharacterService characterService;

    @Autowired
    CharRelicSetService charRelicSetService;

    @Autowired
    CharMainStatService charMainStatService;

    @Autowired
    CharStatService charStatService;

    @Autowired
    CharBuildSugService charBuildSugService;

    @Autowired
    CharOptionsService charOptionsService;

    @Override
    public Boolean addCharacterBuild(CharacterBuild characterBuild) {
        StarRailCharacter character = new StarRailCharacter();
        character.setCharacterName(characterBuild.getCharacterName());
        character.setIsHide(false);
        characterService.addCharacter(character);
        Integer characterId = character.getCharacterId();

        CharOptions charOptions = new CharOptions();
        charOptions.setCharacterId(characterId);
        charOptions.setCanRainbowBuild(characterBuild.getCanRainbowBuild());
        charOptionsService.addCharOptions(charOptions);

        List<RelicSetVO> relicSetVOList = characterBuild.getRelicSets();
        for(RelicSetVO relicSetVO : relicSetVOList) {
            charRelicSetService.addCharRelicSet(convertService.toPO(relicSetVO, characterId));
        }

        List<MainStatVO> mainStatVOList = characterBuild.getMainStats();
        for(MainStatVO mainStatVO : mainStatVOList) {
            charMainStatService.addCharMainStat(convertService.toPO(mainStatVO, characterId));
        }

        List<SubStatVO> subStatVOList = characterBuild.getSubStats();
        for(SubStatVO subStatVO : subStatVOList) {
            charStatService.addCharStat(convertService.toPO(subStatVO, characterId));
        }

        CharBuildSug charBuildSug = new CharBuildSug();
        charBuildSug.setCharacterId(characterId);
        charBuildSug.setBuildSug(characterBuild.getBuildSug());
        charBuildSugService.addCharBuildSug(charBuildSug);

        return true;
    }

    @Override
    public Boolean updateCharacterBuild(CharacterBuild characterBuild) {
        Integer characterId = characterBuild.getCharacterId();

        StarRailCharacter character = new StarRailCharacter();
        character.setCharacterId(characterId);
        character.setCharacterName(characterBuild.getCharacterName());
        character.setIsHide(false);
        characterService.updateCharacter(character);

        CharOptions charOptions = new CharOptions();
        charOptions.setCharacterId(characterId);
        charOptions.setCanRainbowBuild(characterBuild.getCanRainbowBuild());
        charOptionsService.updateCharOptions(charOptions);

        List<CharMainStat> charMainStatList = charMainStatService.getMainStatByChar(characterId);
        List<MainStatVO> mainStatVOList = characterBuild.getMainStats();
        charMainStatService.addCharMainStatList(charMainStatAddList(charMainStatList, mainStatVOList));
        charMainStatService.delCharMainStatList(charMainStatDelList(charMainStatList, mainStatVOList));

        List<CharRelicSet> charRelicSetList = charRelicSetService.getCharRelicSetById(characterId);
        List<RelicSetVO> relicSetVOList = characterBuild.getRelicSets();
        charRelicSetService.addCharRelicSetList(charRelicSetAddList(charRelicSetList, relicSetVOList));
        charRelicSetService.delCharRelicSetList(charRelicSetDelList(charRelicSetList, relicSetVOList));

        List<CharStat> charStatList = charStatService.getCharStatById(characterId);
        List<SubStatVO> subStatVOList = characterBuild.getSubStats();
        charStatService.addCharStatList(charStatAddList(charStatList, subStatVOList));
        charStatService.delCharStatList(charStatDelList(charStatList, subStatVOList));

        CharBuildSug charBuildSug = charBuildSugService.getCharBuildSug(characterId);
        charBuildSug.setBuildSug(characterBuild.getBuildSug());
        charBuildSugService.updateCharBuildSug(charBuildSug);

        return true;
    }

    @Override
    public CharacterBuild getCharacterBuild(Integer characterId) {
        CharacterBuild characterBuild = new CharacterBuild();

        StarRailCharacter character = characterService.getById(characterId);
        characterBuild.setCharacterId(characterId);
        characterBuild.setCharacterName(character.getCharacterName());

        CharOptions charOptions = charOptionsService.getById(characterId);
        characterBuild.setCanRainbowBuild(charOptions.getCanRainbowBuild());

        List<CharMainStat> charMainStatList = charMainStatService.getMainStatByChar(characterId);
        List<MainStatVO> mainStatVOList = new ArrayList<>();
        for(CharMainStat charMainStat : charMainStatList) {
            mainStatVOList.add(convertService.toVO(charMainStat));
        }
        characterBuild.setMainStats(mainStatVOList);

        List<CharRelicSet> charRelicSetList = charRelicSetService.getCharRelicSetById(characterId);
        List<RelicSetVO> relicSetVOList = new ArrayList<>();
        for(CharRelicSet charRelicSet : charRelicSetList) {
            relicSetVOList.add(convertService.toVO(charRelicSet));
        }
        characterBuild.setRelicSets(relicSetVOList);

        List<CharStat> charStatList = charStatService.getCharStatById(characterId);
        List<SubStatVO> subStatVOList = new ArrayList<>();
        for(CharStat charStat : charStatList) {
            subStatVOList.add(convertService.toVO(charStat));
        }
        characterBuild.setSubStats(subStatVOList);

        CharBuildSug charBuildSug = charBuildSugService.getCharBuildSug(characterId);
        characterBuild.setBuildSug(charBuildSug.getBuildSug());

        return characterBuild;
    }

    private List<CharMainStat> charMainStatDelList(List<CharMainStat> charMainStatList, List<MainStatVO> voList) {
        List<CharMainStat> delList = new ArrayList<>();
        Set<String> flagSet = new HashSet<>();
        for(MainStatVO vo : voList) {
            flagSet.add(cacheService.getStatByName(vo.getStatName()).getStatId() + "_" + vo.getRelicType());
        }
        for(CharMainStat charMainStat : charMainStatList) {
            String flag = charMainStat.getStatId() + "_" + charMainStat.getRelicTypeId();
            if(!flagSet.contains(flag)) {
                delList.add(charMainStat);
            }
        }
        return delList;
    }

    private List<CharMainStat> charMainStatAddList(List<CharMainStat> charMainStatList, List<MainStatVO> voList) {
        List<CharMainStat> addList = new ArrayList<>();
        Set<String> flagSet = new HashSet<>();
        Integer characterId = charMainStatList.get(0).getCharacterId();
        for(CharMainStat charMainStat : charMainStatList) {
            flagSet.add(charMainStat.getStatId() + "_" + charMainStat.getRelicTypeId());
        }
        for(MainStatVO vo : voList) {
            String flag = cacheService.getStatByName(vo.getStatName()).getStatId() + "_" + vo.getRelicType();
            if(!flagSet.contains(flag)) {
                addList.add(convertService.toPO(vo, characterId));
            }
        }
        return addList;
    }

    private List<CharRelicSet> charRelicSetDelList(List<CharRelicSet> charRelicSetList, List<RelicSetVO> voList) {
        List<CharRelicSet> delList = new ArrayList<>();
        Set<String> flagSet = new HashSet<>();
        for(RelicSetVO vo : voList) {
            flagSet.add(cacheService.getRelicSetByName(vo.getRelicSetName()).getRelicSetId() + "_" + vo.getEffectDemand());
        }
        for(CharRelicSet charRelicSet : charRelicSetList) {
            String flag = charRelicSet.getRelicSetId() + "_" + charRelicSet.getEffectDemand();
            if(!flagSet.contains(flag)) {
                delList.add(charRelicSet);
            }
        }
        return delList;
    }

    private List<CharRelicSet> charRelicSetAddList(List<CharRelicSet> charRelicSetList, List<RelicSetVO> voList) {
        List<CharRelicSet> addList = new ArrayList<>();
        Set<String> flagSet = new HashSet<>();
        Integer characterId = charRelicSetList.get(0).getCharacterId();
        for(CharRelicSet charRelicSet : charRelicSetList) {
            flagSet.add(charRelicSet.getRelicSetId() + "_" + charRelicSet.getEffectDemand());
        }
        for(RelicSetVO vo : voList) {
            String flag = cacheService.getRelicSetByName(vo.getRelicSetName()).getRelicSetId() + "_" + vo.getEffectDemand();
            if(!flagSet.contains(flag)) {
                addList.add(convertService.toPO(vo, characterId));
            }
        }
        return addList;
    }

    private List<CharStat> charStatDelList(List<CharStat> charStatList, List<SubStatVO> voList) {
        List<CharStat> delList = new ArrayList<>();
        Set<String> flagSet = new HashSet<>();
        for(SubStatVO vo : voList) {
            flagSet.add(cacheService.getStatByName(vo.getStatName()).getStatId() + "_" + vo.getPriority());
        }
        for(CharStat charStat : charStatList) {
            String flag = charStat.getStatId() + "_" + charStat.getPriority();
            if(!flagSet.contains(flag)) {
                delList.add(charStat);
            }
        }
        return delList;
    }

    private List<CharStat> charStatAddList(List<CharStat> charStatList, List<SubStatVO> voList) {
        List<CharStat> addList = new ArrayList<>();
        Set<String> flagSet = new HashSet<>();
        Integer characterId = charStatList.get(0).getCharacterId();
        for(CharStat charStat : charStatList) {
            flagSet.add(charStat.getStatId() + "_" + charStat.getPriority());
        }
        for(SubStatVO vo : voList) {
            String flag = cacheService.getStatByName(vo.getStatName()).getStatId() + "_" + vo.getPriority();
            if(!flagSet.contains(flag)) {
                addList.add(convertService.toPO(vo, characterId));
            }
        }
        return addList;
    }

}
