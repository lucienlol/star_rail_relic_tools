package com.example.starrail.controller;

import com.example.starrail.entity.RelicFitQuery;
import com.example.starrail.po.RelicEntity;
import com.example.starrail.po.RelicFit;
import com.example.starrail.po.StarRailCharacter;
import com.example.starrail.service.ConvertService;
import com.example.starrail.service.RelicCheckService;
import com.example.starrail.service.RelicFitService;
import com.example.starrail.vo.RelicFitReq;
import com.example.starrail.vo.RelicFitDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/relic-fit")
public class RelicFitController {

    @Autowired
    RelicCheckService relicCheckService;

    @Autowired
    ConvertService convertService;

    @Autowired
    RelicFitService relicFitService;

    @PostMapping("/api/add")
    @ResponseBody
    public boolean addRelicFit(@RequestBody RelicFitReq req) {
        List<StarRailCharacter> characterList = convertService.toCharacterList(req.getCharacters());
        List<RelicEntity> relicEntityList = convertService.toRelicList(req.getRelics());
        List<RelicFit> relicFitList = relicCheckService.genRelicFit(characterList, relicEntityList);
        relicFitService.addRelicFitList(relicFitList);
        return true;
    }

    @PostMapping("/api/update")
    @ResponseBody
    public boolean updateRelicFit(@RequestBody RelicFitReq req) {
        List<String> characters = req.getCharacters();
        List<String> relics = req.getRelics();

        RelicFitQuery relicFitQuery = new RelicFitQuery();
        if(!characters.contains("all")) {
            relicFitQuery.setCharacterIdList(convertService.toIntList(characters));
        }
        if(!relics.contains("all")) {
            relicFitQuery.setRelicIdList(convertService.toIntList(relics));
        }
        relicFitService.deleteRelicFitByQuery(relicFitQuery);

        List<StarRailCharacter> characterList = convertService.toCharacterList(req.getCharacters());
        List<RelicEntity> relicEntityList = convertService.toRelicList(req.getRelics());
        List<RelicFit> relicFitList = relicCheckService.genRelicFit(characterList, relicEntityList);
        relicFitService.addRelicFitList(relicFitList);

        return true;
    }

    @DeleteMapping("/api/delete")
    @ResponseBody
    public boolean deleteRelicFit(@RequestBody RelicFitReq req) {
        List<String> characters = req.getCharacters();
        List<String> relics = req.getRelics();

        RelicFitQuery relicFitQuery = new RelicFitQuery();
        if(!characters.contains("all")) {
            relicFitQuery.setCharacterIdList(convertService.toIntList(characters));
        }
        if(!relics.contains("all")) {
            relicFitQuery.setRelicIdList(convertService.toIntList(relics));
        }
        relicFitService.deleteRelicFitByQuery(relicFitQuery);
        return true;
    }

    @GetMapping("/list")
    public String getRelicFitPage() {
        return "html/relic-fit-list";
    }

    @GetMapping("/api/list")
    @ResponseBody
    public List<RelicFitDetailVO> getRelicFitList(@RequestBody RelicFitReq req) {
        return relicFitService.getDetailByQuery(convertService.toQuery(req)).stream().map(convertService::toVO).collect(Collectors.toList());
    }
}
