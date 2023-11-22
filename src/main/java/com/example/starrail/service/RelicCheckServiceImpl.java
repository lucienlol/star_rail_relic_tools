package com.example.starrail.service;

import com.example.starrail.po.*;
import com.example.starrail.vo.CharacterCheckVO;
import com.example.starrail.vo.RelicEntityVO;
import com.example.starrail.vo.StatValueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.starrail.service.StarRailUtil.formatPrecision;

@Service
public class RelicCheckServiceImpl implements RelicCheckService{

    @Autowired
    CharacterService characterService;

    @Autowired
    CharOptionsService charOptionsService;

    @Autowired
    CharMainStatService charMainStatService;

    @Autowired
    CharStatService charStatService;

    @Autowired
    CharRelicSetService charRelicSetService;

    @Autowired
    CacheService cacheService;

    HashMap<Integer, List<Integer>> charRelicSet4Map = new HashMap<>();

    HashMap<Integer, List<Integer>> charRelicSet2Map = new HashMap<>();

    HashMap<Integer, Boolean> rainbowBuildMap = new HashMap<>();

    HashMap<Integer, Map<Integer, Integer>> charStatPriorityMap = new HashMap<>();

    HashMap<Integer, Map<Integer, List<Integer>>> charMainStatMap = new HashMap<>();

    HashMap<Integer, Double> charMax3StatValue = new HashMap<>();

    HashMap<Integer, Double> charMax4StatValue = new HashMap<>();

    HashMap<Integer, Double> maxStatPriority = new HashMap<>();

    HashMap<String, Double> statValueMap = new HashMap<>() {{
        put("生命值", 42.0);
        put("攻击力", 21.0);
        put("防御力", 21.0);
        put("生命值百分比", 4.32);
        put("攻击力百分比", 4.32);
        put("防御力百分比", 5.4);
        put("速度", 2.6);
        put("暴击率", 3.24);
        put("暴击伤害", 6.48);
        put("击破特攻", 6.48);
        put("效果命中", 4.32);
        put("效果抵抗", 4.32);
    }};

    @Override
    public void prepare() {
        System.out.println("begin prepare");

        charRelicSet4Map.clear();
        charRelicSet2Map.clear();
        rainbowBuildMap.clear();
        charStatPriorityMap.clear();
        charMainStatMap.clear();
        charMax3StatValue.clear();
        charMax4StatValue.clear();

        List<StarRailCharacter> characterList = characterService.getAllShow();

        // generate charRelicMap
        for(StarRailCharacter character : characterList) {
            List<Integer> relicSet4List = new ArrayList<>();
            List<Integer> relicSet2List = new ArrayList<>();
            List<CharRelicSet> charRelicSetList = charRelicSetService.getCharRelicSetById(character.getCharacterId());
            for(CharRelicSet charRelicSet : charRelicSetList) {
                if(charRelicSet.getEffectDemand() == 1) {
                    relicSet2List.add(charRelicSet.getRelicSetId());
                } else {
                    relicSet4List.add(charRelicSet.getRelicSetId());
                }
            }
            charRelicSet2Map.put(character.getCharacterId(), relicSet2List);
            charRelicSet4Map.put(character.getCharacterId(), relicSet4List);
        }

        // generate rainbowBuildMap
        List<CharOptions> charOptionsList = charOptionsService.getAll();
        for(CharOptions charOptions : charOptionsList) {
            rainbowBuildMap.put(charOptions.getCharacterId(), charOptions.getCanRainbowBuild());
        }

        // generate charMainStatMap
        for(StarRailCharacter character : characterList) {
            List<CharMainStat> charMainStatList = charMainStatService.getMainStatByChar(character.getCharacterId());
            HashMap<Integer, List<Integer>> mainStatMap = new HashMap<>();
            for(CharMainStat charMainStat : charMainStatList) {
                Integer relicType = charMainStat.getRelicTypeId();
                Integer statId = charMainStat.getStatId();
                if(mainStatMap.containsKey(relicType)) {
                    mainStatMap.get(relicType).add(statId);
                } else {
                    mainStatMap.put(relicType, new ArrayList<>(Collections.singletonList(statId)));
                }
            }
            charMainStatMap.put(character.getCharacterId(), mainStatMap);
        }


        // generate other maps
        for(StarRailCharacter character : characterList) {
            int statCount1 = 0;
            int statCount2 = 0;
            int statCount3 = 0;
            int statCount4 = 0;

            List<CharStat> charStatList = charStatService.getCharStatById(character.getCharacterId());
            HashMap<Integer, Integer> priorityMap = new HashMap<>();
            for(CharStat charStat : charStatList) {
                priorityMap.put(charStat.getStatId(), charStat.getPriority());
                switch (charStat.getPriority()) {
                    case 1 -> statCount1++;
                    case 2 -> statCount2++;
                    case 3 -> statCount3++;
                    case 4 -> statCount4++;
                }
            }
            charStatPriorityMap.put(character.getCharacterId(), priorityMap);

            double[] weightedValue = new double[5];
            weightedValue[0] = 0;
            int countedNum = 0;
            int index = 1;
            while(index <= 4 && countedNum < statCount1) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(1);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while(index <= 4 && countedNum < statCount2) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(2);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while(index <= 4 && countedNum < statCount3) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(3);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while(index <= 4 && countedNum < statCount4) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(4);
                index++;
                countedNum++;
            }
            while(index <= 4) {
                weightedValue[index] = weightedValue[index - 1];
                index++;
            }

            charMax3StatValue.put(character.getCharacterId(), weightedValue[3]);
            charMax4StatValue.put(character.getCharacterId(), weightedValue[4]);
            maxStatPriority.put(character.getCharacterId(), weightedValue[1]);
        }
    }

    @Override
    public List<CharacterCheckVO> doCheck(RelicEntityVO relicEntityVO) {
        List<CharacterCheckVO> voList = new ArrayList<>();
        List<StarRailCharacter> characterList = characterService.getAllShow();

        // gen sub stat enhance list
        List<StatEnhance> statEnhanceList = new ArrayList<>();
        for(StatValueVO statValueVO : relicEntityVO.getSubStatList()) {
            Stat stat = cacheService.getStatByName(statValueVO.getStatName());
            StatEnhance statEnhance = new StatEnhance();

            statEnhance.setStatName(stat.getStatName());
            statEnhance.setStatId(stat.getStatId());
            Double enhanceMaxValue = statValueMap.get(stat.getStatName());
            Double statValue = enhanceMaxValue;
            try {
                statValue = Double.parseDouble(statValueVO.getValue());
            } catch (Exception ignored) {}
            int enhanceTimes = (int) Math.ceil(statValue / enhanceMaxValue);
            statEnhance.setStatValue(statValue);
            statEnhance.setEnhanceTimes(enhanceTimes);
            statEnhance.setEnhanceRate(formatPrecision(statValue / enhanceMaxValue / enhanceTimes));
            statEnhanceList.add(statEnhance);
        }

        for(StarRailCharacter character : characterList) {
            Integer characterId = character.getCharacterId();
            CharacterCheckVO characterCheckVO = new CharacterCheckVO();
            characterCheckVO.setCharacterId(characterId);
            characterCheckVO.setCharacterName(character.getCharacterName());

            // check relic set
            Integer relicSetId = -1;
            if(!relicEntityVO.getRelicSetList().isEmpty()) {
                String relicSetName = relicEntityVO.getRelicSetList().get(0);
                relicSetId = cacheService.getRelicSetByName(relicSetName).getRelicSetId();
            }

            StringBuilder relicFitMsg = new StringBuilder("");
            if(charRelicSet4Map.get(characterId).contains(relicSetId)) {
                characterCheckVO.setIsRelicSetRight(1);
                relicFitMsg.append("全套效果 ");
            }
            if(charRelicSet2Map.get(characterId).contains(relicSetId)) {
                characterCheckVO.setIsRelicSetRight(1);
                relicFitMsg.append("半套效果 ");
            }
            if(rainbowBuildMap.get(characterId)) {
                characterCheckVO.setIsRelicSetRight(1);
                relicFitMsg.append("散件");
            }
            if(characterCheckVO.getIsRelicSetRight() == 0) {
                relicFitMsg.append("无套装效果");
            }
            characterCheckVO.setRelicFitMsg(relicFitMsg.toString().trim());

            // check main stat
            Integer mainStatId = -1;
            Integer relicTypeId = -1;
            if(!relicEntityVO.getMainStatList().isEmpty()) {
                String mainStatName = relicEntityVO.getMainStatList().get(0);
                mainStatId = cacheService.getStatByName(mainStatName).getStatId();
            }
            if(relicEntityVO.getRelicTypeList().size() == 1) {
                String relicTypeName = relicEntityVO.getRelicTypeList().get(0);
                relicTypeId = cacheService.getRelicTypeByName(relicTypeName).getRelicTypeId();
            }

            StringBuilder mainStatFitMsg = new StringBuilder("");
            if(Objects.equals(relicTypeId, StarRailUtil.HEAD_TYPE) || Objects.equals(relicTypeId, StarRailUtil.HAND_TYPE)) {
                characterCheckVO.setIsMainStatRight(1);
                mainStatFitMsg.append("主词条正确");
            } else {
                List<Integer> mainStatList = charMainStatMap.get(characterId).get(relicTypeId);
                if(mainStatList.contains(mainStatId)) {
                    characterCheckVO.setIsMainStatRight(1);
                    mainStatFitMsg.append("主词条正确");
                } else {
                    characterCheckVO.setIsMainStatRight(0);
                    mainStatFitMsg.append("主词条应为:");
                    for(Integer id : mainStatList) {
                        mainStatFitMsg.append(cacheService.getStatById(id).getStatName()).append(",");
                    }
                    mainStatFitMsg.deleteCharAt(mainStatFitMsg.length() - 1);
                }
            }
            characterCheckVO.setMainStatFitMsg(mainStatFitMsg.toString());

            // check sub stat
            if(statEnhanceList.size() > 4 || statEnhanceList.size() < 3) {
                characterCheckVO.setStatFitMsg("词条数量错误");
            } else {
                double weightedValue = 0.0;
                Map<Integer, Integer> statPriorityMap = charStatPriorityMap.get(characterId);
                StringBuilder sb = new StringBuilder("");
                for(StatEnhance statEnhance : statEnhanceList) {
                    Integer priority = 0;
                    if(statPriorityMap.containsKey(statEnhance.getStatId())) {
                        priority = statPriorityMap.get(statEnhance.getStatId());
                    }

                    weightedValue += statEnhance.getStatValue() / statValueMap.get(statEnhance.statName) * getWeightedValue(priority);
                    sb.append(statEnhance.getStatName()).append("--").append("优先级权值:").
                            append(getWeightedValue(priority)).append(",").append("强化次数:").
                            append(statEnhance.getEnhanceTimes()).append(",").append("强满率")
                            .append(statEnhance.getEnhanceRate()).append(" ");
                }
                characterCheckVO.setStatFitMsg(sb.toString().trim());

                if(statEnhanceList.size() == 3) {
                    characterCheckVO.setStatFitness(formatPrecision(weightedValue / charMax3StatValue.get(characterId)));
                } else {
                    double dreamWeightedValue = charMax4StatValue.get(characterId) + (relicEntityVO.getRelicLevel()) *
                            maxStatPriority.get(characterId);
                    characterCheckVO.setStatFitness(formatPrecision(weightedValue / dreamWeightedValue));
                }
            }
            voList.add(characterCheckVO);
        }

        Collections.sort(voList);

        return voList;
    }

    @Override
    public List<RelicFit> genRelicFit(List<StarRailCharacter> characterList, List<RelicEntity> relicList) {
        List<RelicFit> relicFitList = new ArrayList<>();
        HashMap<Integer, List<Integer>> charRelicSet4Map = new HashMap<>();
        HashMap<Integer, List<Integer>> charRelicSet2Map = new HashMap<>();
        HashMap<Integer, Boolean> rainbowBuildMap = new HashMap<>();
        HashMap<Integer, Map<Integer, Integer>> charStatPriorityMap = new HashMap<>();
        HashMap<Integer, Map<Integer, List<Integer>>> charMainStatMap = new HashMap<>();
        HashMap<Integer, Double> charMax3StatValue = new HashMap<>();
        HashMap<Integer, Double> charMax4StatValue = new HashMap<>();
        HashMap<Integer, Double> maxStatPriority = new HashMap<>();


        for (StarRailCharacter character : characterList) {
            List<Integer> relicSet4List = new ArrayList<>();
            List<Integer> relicSet2List = new ArrayList<>();
            List<CharRelicSet> charRelicSetList = charRelicSetService.getCharRelicSetById(character.getCharacterId());
            for (CharRelicSet charRelicSet : charRelicSetList) {
                if (charRelicSet.getEffectDemand() == 1) {
                    relicSet2List.add(charRelicSet.getRelicSetId());
                } else {
                    relicSet4List.add(charRelicSet.getRelicSetId());
                }
            }
            charRelicSet2Map.put(character.getCharacterId(), relicSet2List);
            charRelicSet4Map.put(character.getCharacterId(), relicSet4List);

            // generate rainbowBuildMap
            CharOptions charOption = charOptionsService.getById(character.getCharacterId());
            rainbowBuildMap.put(charOption.getCharacterId(), charOption.getCanRainbowBuild());

            // generate charMainStatMap
            List<CharMainStat> charMainStatList = charMainStatService.getMainStatByChar(character.getCharacterId());
            HashMap<Integer, List<Integer>> mainStatMap = new HashMap<>();
            for (CharMainStat charMainStat : charMainStatList) {
                Integer relicType = charMainStat.getRelicTypeId();
                Integer statId = charMainStat.getStatId();
                if (mainStatMap.containsKey(relicType)) {
                    mainStatMap.get(relicType).add(statId);
                } else {
                    mainStatMap.put(relicType, new ArrayList<>(Collections.singletonList(statId)));
                }
            }
            charMainStatMap.put(character.getCharacterId(), mainStatMap);

            // generate other maps
            int statCount1 = 0;
            int statCount2 = 0;
            int statCount3 = 0;
            int statCount4 = 0;

            List<CharStat> charStatList = charStatService.getCharStatById(character.getCharacterId());
            HashMap<Integer, Integer> priorityMap = new HashMap<>();
            for (CharStat charStat : charStatList) {
                priorityMap.put(charStat.getStatId(), charStat.getPriority());
                switch (charStat.getPriority()) {
                    case 1 -> statCount1++;
                    case 2 -> statCount2++;
                    case 3 -> statCount3++;
                    case 4 -> statCount4++;
                }
            }
            charStatPriorityMap.put(character.getCharacterId(), priorityMap);

            double[] weightedValue = new double[5];
            weightedValue[0] = 0;
            int countedNum = 0;
            int index = 1;
            while (index <= 4 && countedNum < statCount1) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(1);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 4 && countedNum < statCount2) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(2);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 4 && countedNum < statCount3) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(3);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 4 && countedNum < statCount4) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(4);
                index++;
                countedNum++;
            }
            while (index <= 4) {
                weightedValue[index] = weightedValue[index - 1];
                index++;
            }

            charMax3StatValue.put(character.getCharacterId(), weightedValue[3]);
            charMax4StatValue.put(character.getCharacterId(), weightedValue[4]);
            maxStatPriority.put(character.getCharacterId(), weightedValue[1]);
        }

        for(RelicEntity relicEntity : relicList) {
            // gen sub stat enhance list
            List<StatEnhance> statEnhanceList = new ArrayList<>();
            String[] subStats = relicEntity.getSubStatValues().split("\n");
            for(String subStatStr : subStats) {
                String statName = subStatStr.split(":")[0];
                String valueStr = subStatStr.split(":")[1];
                Stat stat = cacheService.getStatByName(statName);
                StatEnhance statEnhance = new StatEnhance();

                statEnhance.setStatName(stat.getStatName());
                statEnhance.setStatId(stat.getStatId());
                Double enhanceMaxValue = statValueMap.get(statName);
                Double statValue = enhanceMaxValue;
                try {
                    statValue = Double.parseDouble(valueStr);
                } catch (Exception ignored) {}
                int enhanceTimes = (int) Math.ceil(statValue / enhanceMaxValue);
                statEnhance.setStatValue(statValue);
                statEnhance.setEnhanceTimes(enhanceTimes);
                statEnhance.setEnhanceRate(formatPrecision(statValue / enhanceMaxValue / enhanceTimes));
                statEnhanceList.add(statEnhance);
            }

            for(StarRailCharacter character : characterList) {
                Integer characterId = character.getCharacterId();
                RelicFit relicFit = new RelicFit();
                relicFit.setRelicId(relicEntity.getRelicEntityId());
                relicFit.setCharacterId(characterId);

                Integer relicSetId = relicEntity.getRelicSetId();
                relicFit.setIsRelicSetFit(charRelicSet4Map.get(characterId).contains(relicSetId) || charRelicSet2Map.get(characterId).
                        contains(relicSetId) || rainbowBuildMap.get(characterId));


                if(Objects.equals(relicEntity.getRelicTypeId(), StarRailUtil.HEAD_TYPE)
                        || Objects.equals(relicEntity.getRelicTypeId(), StarRailUtil.HAND_TYPE)) {
                    relicFit.setIsMainStatFit(true);
                } else {
                    List<Integer> mainStatList = charMainStatMap.get(characterId).get(relicEntity.getRelicTypeId());
                    relicFit.setIsMainStatFit(mainStatList.contains(relicEntity.getMainStatId()));
                }

                // check sub stat
                if(statEnhanceList.size() > 4 || statEnhanceList.size() < 3) {
                    relicFit.setSubStatFitness(0.0);
                    relicFit.setSubStatFitDesc("词条数量错误");
                } else {
                    double weightedValue = 0.0;
                    Map<Integer, Integer> statPriorityMap = charStatPriorityMap.get(characterId);
                    StringBuilder sb = new StringBuilder("");
                    for(StatEnhance statEnhance : statEnhanceList) {
                        Integer priority = 0;
                        if(statPriorityMap.containsKey(statEnhance.getStatId())) {
                            priority = statPriorityMap.get(statEnhance.getStatId());
                        }

                        weightedValue += statEnhance.getStatValue() / statValueMap.get(statEnhance.statName) * getWeightedValue(priority);
                        sb.append(statEnhance.getStatName()).append("--").append("优先级权值:").
                                append(getWeightedValue(priority)).append(",").append("强化次数:").
                                append(statEnhance.getEnhanceTimes()).append(",").append("强满率")
                                .append(statEnhance.getEnhanceRate()).append(" ");
                    }
                    relicFit.setSubStatFitDesc(sb.toString().trim());

                    if(statEnhanceList.size() == 3) {
                        relicFit.setSubStatFitness(formatPrecision(weightedValue / charMax3StatValue.get(characterId)));
                    } else {
                        double dreamWeightedValue = charMax4StatValue.get(characterId) + (relicEntity.getRelicLevel() / 3) *
                                maxStatPriority.get(characterId);
                        relicFit.setSubStatFitness(formatPrecision(weightedValue / dreamWeightedValue));
                    }
                }
                relicFitList.add(relicFit);
            }
        }
        return relicFitList;
    }

    private double getWeightedValue(Integer priority) {
        return switch (priority) {
            case 1 -> 1.0;
            case 2 -> 0.75;
            case 3 -> 0.5;
            case 4 -> 0.25;
            default -> 0;
        };
    }

    class StatEnhance {
        String statName;
        Integer statId;
        Double statValue;
        Integer enhanceTimes;
        Double enhanceRate;

        public String getStatName() {
            return statName;
        }

        public void setStatName(String statName) {
            this.statName = statName;
        }

        public Integer getStatId() {
            return statId;
        }

        public void setStatId(Integer statId) {
            this.statId = statId;
        }

        public Double getStatValue() {
            return statValue;
        }

        public void setStatValue(Double statValue) {
            this.statValue = statValue;
        }

        public Integer getEnhanceTimes() {
            return enhanceTimes;
        }

        public void setEnhanceTimes(Integer enhanceTimes) {
            this.enhanceTimes = enhanceTimes;
        }

        public Double getEnhanceRate() {
            return enhanceRate;
        }

        public void setEnhanceRate(Double enhanceRate) {
            this.enhanceRate = enhanceRate;
        }
    }
}
