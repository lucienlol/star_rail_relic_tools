package com.example.starrail.service;

import com.example.starrail.po.*;
import com.example.starrail.vo.RelicFitDetailVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.starrail.service.StarRailUtil.formatPrecision;
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

    @Autowired
    CharRelicSetService charRelicSetService;

    @Autowired
    CharMainStatService charMainStatService;

    @Autowired
    CharOptionsService charOptionsService;

    @Autowired
    CharStatService charStatService;

    @Autowired
    ConvertService convertService;

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

    @Test
    void genRelicFit() {
        List<StarRailCharacter> characterList = convertService.toCharacterList(Collections.singletonList("all"));
        List<RelicEntity> relicList = convertService.toRelicList(Collections.singletonList("all"));

        List<RelicFit> relicFitList = new ArrayList<>();
        HashMap<Integer, List<Integer>> charRelicSet4Map = new HashMap<>();
        HashMap<Integer, List<Integer>> charRelicSet2Map = new HashMap<>();
        HashMap<Integer, Boolean> rainbowBuildMap = new HashMap<>();
        HashMap<Integer, Map<Integer, Integer>> charStatPriorityMap = new HashMap<>();
        HashMap<Integer, Map<Integer, List<Integer>>> charMainStatMap = new HashMap<>();
        HashMap<Integer, Double> charMax3StatValue = new HashMap<>();
        HashMap<Integer, Double> charMax4StatValue = new HashMap<>();
        HashMap<Integer, Double> charMax5StatValue = new HashMap<>();
        HashMap<Integer, Double> maxStatPriority = new HashMap<>();
        HashMap<Integer, Double> backupStatPriority = new HashMap<>();

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
            int statCount5 = 0;

            List<CharStat> charStatList = charStatService.getCharStatById(character.getCharacterId());
            HashMap<Integer, Integer> priorityMap = new HashMap<>();
            for (CharStat charStat : charStatList) {
                priorityMap.put(charStat.getStatId(), charStat.getPriority());
                switch (charStat.getPriority()) {
                    case 1 -> statCount1++;
                    case 2 -> statCount2++;
                    case 3 -> statCount3++;
                    case 4 -> statCount4++;
                    case 5 -> statCount5++;
                }
            }
            charStatPriorityMap.put(character.getCharacterId(), priorityMap);

            double[] weightedValue = new double[6];
            weightedValue[0] = 0;
            int countedNum = 0;
            int index = 1;
            while (index <= 5 && countedNum < statCount1) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(1);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 5 && countedNum < statCount2) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(2);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 5 && countedNum < statCount3) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(3);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 5 && countedNum < statCount4) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(4);
                index++;
                countedNum++;
            }
            countedNum = 0;
            while (index <= 5 && countedNum < statCount5) {
                weightedValue[index] = weightedValue[index - 1] + getWeightedValue(5);
                index++;
                countedNum++;
            }
            while (index <= 5) {
                weightedValue[index] = weightedValue[index - 1];
                index++;
            }

            charMax3StatValue.put(character.getCharacterId(), weightedValue[3]);
            charMax4StatValue.put(character.getCharacterId(), weightedValue[4]);
            charMax5StatValue.put(character.getCharacterId(), weightedValue[5]);
            maxStatPriority.put(character.getCharacterId(), weightedValue[1]);
            backupStatPriority.put(character.getCharacterId(), weightedValue[2] - weightedValue[1]);
        }

        for(RelicEntity relicEntity : relicList) {
            // gen sub stat enhance list
            List<StatEnhance> statEnhanceList = new ArrayList<>();
            String[] subStats = relicEntity.getSubStatValues().split("\n");

            List<RelicFit> singleRelicFitList = new ArrayList<>();

            // 增加包含速度判断
            boolean containSpeed = false;
            if(relicEntity.getMainStatId() == 7) {
                containSpeed = true; // 主词条为速度
            }

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

                if(statName.equals("速度")) {
                    containSpeed = true;
                }
            }

            for(StarRailCharacter character : characterList) {
                Integer characterId = character.getCharacterId();
                RelicFit relicFit = new RelicFit();
                relicFit.setRelicId(relicEntity.getRelicEntityId());
                relicFit.setCharacterId(characterId);

                Integer relicSetId = relicEntity.getRelicSetId();
                relicFit.setIsRelicSetFit(charRelicSet4Map.get(characterId).contains(relicSetId) || charRelicSet2Map.get(characterId).
                        contains(relicSetId) || rainbowBuildMap.get(characterId));

                // 增加是否为推荐套装判断
                boolean isRelicSetRecommend = charRelicSet4Map.get(characterId).contains(relicSetId) || charRelicSet2Map.get(characterId).
                        contains(relicSetId);

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
                    int totalStatNums = 0;
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

                        totalStatNums += statEnhance.enhanceTimes;
                    }
                    relicFit.setSubStatFitDesc(sb.toString().trim());

                    int mainStatPriority = statPriorityMap.getOrDefault(relicEntity.getMainStatId(), 0);
                    double mainStatWeightedValue = 0;
                    if(mainStatPriority != 0) {
                        mainStatWeightedValue += getWeightedValue(mainStatPriority);
                    }
                    if(statEnhanceList.size() == 3) {
                        double dreamWeightedValue = charMax3StatValue.get(characterId);
                        if(mainStatWeightedValue > (charMax4StatValue.get(characterId) - charMax3StatValue.get(characterId))) {
                            dreamWeightedValue = charMax4StatValue.get(characterId) - mainStatWeightedValue;
                        }

                        // 适配度调整
                        double fitnessShiftValue = 0.0;
                        if(Arrays.asList(2, 4).contains(character.getCharacterId())) {
                            if(!containSpeed) {
                                fitnessShiftValue -= 0.05;
                            }
                            if(!isRelicSetRecommend) {
                                fitnessShiftValue -= 0.05;
                            }
                        } else if(Arrays.asList(3,6,9,18,20,22).contains(character.getCharacterId())) {
                            if(!containSpeed) {
                                fitnessShiftValue -= 0.1;
                            }
                            if(!isRelicSetRecommend) {
                                fitnessShiftValue -= 0.05;
                            }
                        }

                        relicFit.setSubStatFitness(formatPrecision(formatPrecision(weightedValue / dreamWeightedValue) + fitnessShiftValue));
                    } else {
                        double dreamWeightedValue = 0;
                        if(mainStatWeightedValue > (charMax5StatValue.get(characterId) - charMax4StatValue.get(characterId))) {
                            dreamWeightedValue += charMax5StatValue.get(characterId) - mainStatWeightedValue;
                            if(mainStatWeightedValue == maxStatPriority.get(characterId)) {
                                dreamWeightedValue += (totalStatNums - 4) * backupStatPriority.get(characterId);
                            } else {
                                dreamWeightedValue += (totalStatNums - 4) * maxStatPriority.get(characterId);
                            }
                        } else {
                            dreamWeightedValue = charMax4StatValue.get(characterId) + (totalStatNums - 4) *
                                    maxStatPriority.get(characterId);
                        }

                        // 判断是否为初始4词条
                        double subStatFitness = (weightedValue / dreamWeightedValue);
                        if((totalStatNums - 4) >= (relicEntity.getRelicLevel() / 3)) {
                            subStatFitness = subStatFitness * 1.125;
                        }

                        // 适配度调整
                        double fitnessShiftValue = 0.0;
                        if(Arrays.asList(2, 4).contains(character.getCharacterId())) {
                            if(!containSpeed) {
                                fitnessShiftValue -= 0.05;
                            }
                            if(!isRelicSetRecommend) {
                                fitnessShiftValue -= 0.05;
                            }
                        } else if(Arrays.asList(3,6,9,18,20,22).contains(character.getCharacterId())) {
                            if(!containSpeed) {
                                fitnessShiftValue -= 0.1;
                            }
                            if(!isRelicSetRecommend) {
                                fitnessShiftValue -= 0.05;
                            }
                        }

                        subStatFitness += fitnessShiftValue;

                        relicFit.setSubStatFitness(formatPrecision(subStatFitness));
                    }
                }

                relicFitList.add(relicFit);
                singleRelicFitList.add(relicFit);
            }

            List<RelicFitDetailVO> relicFitDetailVOList = new ArrayList<>(singleRelicFitList.stream().map(convertService::toVO).toList());
            Collections.sort(relicFitDetailVOList);
            for(RelicFitDetailVO vo : relicFitDetailVOList) {
                if(vo.getRelicSetFit() && vo.getMainStatFit()) {
                    if(vo.getSubStatFitness() >= 0.55) {
                        System.out.println("遗器id:" + vo.getRelicId() + " " + vo.getCharacterName() + ":" + vo.getSubStatFitness());
                    }
                }
            }
        }
    }

    private double getWeightedValue(Integer priority) {
        return switch (priority) {
            case 1 -> 1.0;
            case 2 -> 0.75;
            case 3 -> 0.5;
            case 4 -> 0.25;
            case 5 -> 0.1;
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