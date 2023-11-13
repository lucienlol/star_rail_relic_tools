package com.example.starrail.service;

import com.example.starrail.dao.StatMapper;
import com.example.starrail.po.Stat;
import com.example.starrail.po.StatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StatServiceImpl implements StatService{

    @Autowired
    StatMapper mapper;

    @Override
    public List<Stat> getAllStats() {
        StatExample example = new StatExample();
        return mapper.selectByExample(example);
    }

    @Override
    public List<Stat> getAllMainStats() {
        ArrayList<String> mainStatNames = new ArrayList<>(Arrays.asList("生命值", "攻击力", "生命值百分比", "攻击力百分比",
                "防御力百分比", "暴击率", "暴击伤害", "治疗量加成", "效果命中", "速度", "物理属性伤害提高", "火属性伤害提高",
                "冰属性伤害提高", "雷属性伤害提高", "风属性伤害提高", "量子属性伤害提高", "虚数属性伤害提高", "击破特攻", "能量恢复效率"));
        List<Stat> allStats = getAllStats();
        List<Stat> mainStats = new ArrayList<>();
        for(Stat stat : allStats) {
            if(mainStatNames.contains(stat.getStatName())) {
                mainStats.add(stat);
            }
        }
        return mainStats;
    }

    @Override
    public List<Stat> getAllBodyStats() {
        ArrayList<String> bodyStatNames = new ArrayList<>(Arrays.asList("生命值百分比", "攻击力百分比", "防御力百分比", "暴击率",
                "暴击伤害", "治疗量加成", "效果命中"));
        List<Stat> allStats = getAllStats();
        List<Stat> bodyStats = new ArrayList<>();
        for(Stat stat: allStats) {
            if(bodyStatNames.contains(stat.getStatName())) {
                bodyStats.add(stat);
            }
        }
        return bodyStats;
    }

    @Override
    public List<Stat> getAllFeetStats() {
        ArrayList<String> feetStatNames = new ArrayList<>(Arrays.asList("生命值百分比", "攻击力百分比", "防御力百分比", "速度"));
        List<Stat> allStats = getAllStats();
        List<Stat> feetStats = new ArrayList<>();
        for(Stat stat: allStats) {
            if(feetStatNames.contains(stat.getStatName())) {
                feetStats.add(stat);
            }
        }
        return feetStats;
    }

    @Override
    public List<Stat> getAllSphereStats() {
        ArrayList<String> sphereStatNames = new ArrayList<>(Arrays.asList("生命值百分比", "攻击力百分比", "防御力百分比",
                "物理属性伤害提高", "火属性伤害提高", "冰属性伤害提高", "雷属性伤害提高", "风属性伤害提高", "量子属性伤害提高",
                "虚数属性伤害提高"));
        List<Stat> allStats = getAllStats();
        List<Stat> sphereStats = new ArrayList<>();
        for(Stat stat: allStats) {
            if(sphereStatNames.contains(stat.getStatName())) {
                sphereStats.add(stat);
            }
        }
        return sphereStats;
    }

    @Override
    public List<Stat> getAllRopeStats() {
        ArrayList<String> ropeStatNames = new ArrayList<>(Arrays.asList("生命值百分比", "攻击力百分比", "防御力百分比",
                "击破特攻", "能量恢复效率"));
        List<Stat> allStats = getAllStats();
        List<Stat> ropeStats = new ArrayList<>();
        for(Stat stat: allStats) {
            if(ropeStatNames.contains(stat.getStatName())) {
                ropeStats.add(stat);
            }
        }
        return ropeStats;
    }

    @Override
    public List<Stat> getAllSubStats() {
        ArrayList<String> subStatNames = new ArrayList<>(Arrays.asList("生命值百分比", "攻击力百分比", "防御力百分比",
                "生命值", "攻击力", "防御力", "暴击率", "暴击伤害", "效果命中", "效果抵抗", "击破特攻", "速度"));
        List<Stat> allStats = getAllStats();
        List<Stat> subStats = new ArrayList<>();
        for(Stat stat: allStats) {
            if(subStatNames.contains(stat.getStatName())) {
                subStats.add(stat);
            }
        }
        return subStats;
    }
}
