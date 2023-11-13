package com.example.starrail.controller;

import com.example.starrail.po.RelicSet;
import com.example.starrail.service.RelicSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("relic-set")
public class RelicSetController {

    @Autowired
    RelicSetService relicSetService;

    @PostMapping("/")
    public void addRelicSet(@RequestBody RelicSet relicSet) {
        relicSetService.addRelicSet(relicSet);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<RelicSet> getAllRelicSets() {
        return relicSetService.getAllRelicSets();
    }

    @GetMapping("/carven-relics")
    @ResponseBody
    public List<RelicSet> getAllCarvenRelics() {
        return relicSetService.getAllCavernRelics();
    }

    @GetMapping("/planar-ornaments")
    @ResponseBody
    public List<RelicSet> getAllPlanarOrnaments() {
        return relicSetService.getAllPlanarOrnaments();
    }

    @PostMapping("/batch")
    public void addRelicSets(@RequestBody List<RelicSet> relicSets) {
        relicSetService.addRelicSets(relicSets);
    }
}
