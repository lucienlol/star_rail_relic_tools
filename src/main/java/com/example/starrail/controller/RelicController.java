package com.example.starrail.controller;

import com.example.starrail.entity.RelicFitQuery;
import com.example.starrail.po.RelicEntity;
import com.example.starrail.po.RelicFit;
import com.example.starrail.po.StarRailCharacter;
import com.example.starrail.service.ConvertService;
import com.example.starrail.service.RelicCheckService;
import com.example.starrail.service.RelicEntityService;
import com.example.starrail.service.RelicFitService;
import com.example.starrail.vo.RelicEntityVO;
import com.example.starrail.vo.RelicFitReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("relic")
public class RelicController {

    @Autowired
    RelicEntityService relicEntityService;

    @Autowired
    ConvertService convertService;

    @Autowired
    RelicFitService relicFitService;

    @Autowired
    RelicCheckService relicCheckService;

    @GetMapping("/api/{id}")
    @ResponseBody
    public RelicEntityVO getRelicEntity(@PathVariable Integer id) {
        return convertService.toVO(relicEntityService.getById(id));
    }

    @PostMapping("/api/add")
    @ResponseBody
    public int addRelicEntity(@RequestBody RelicEntityVO vo) {
        RelicEntity relicEntity = convertService.toPO(vo);
        relicEntityService.insertRelicEntity(relicEntity);
        return relicEntity.getRelicEntityId();
    }

    @DeleteMapping("/api/delete/{id}")
    @ResponseBody
    public void deleteRelicEntity(@PathVariable Integer id) {
        relicEntityService.deleteRelicEntity(id);

        RelicFitQuery relicFitQuery = new RelicFitQuery();
        relicFitQuery.setCharacterIdList(Collections.emptyList());
        relicFitQuery.setRelicIdList(Collections.singletonList(id));
        relicFitService.deleteRelicFitByQuery(relicFitQuery);
    }

    @PostMapping("/api/update")
    @ResponseBody
    public void updateRelicEntity(@RequestBody RelicEntityVO vo) {
        relicEntityService.updateRelicEntity(convertService.toPO(vo));
    }

    @GetMapping("/api/all")
    @ResponseBody
    public List<RelicEntityVO> getRelicEntityList() {
        return relicEntityService.getAll().stream().map(convertService::toVO).collect(Collectors.toList());
    }

    @GetMapping("/new")
    public String newRelicPage() {
        return "html/relic";
    }

    @GetMapping("/{id}")
    public String relicPage() {
        return "html/relic";
    }

    @GetMapping("/list")
    public String relicListPage() {
        return "html/relic-list";
    }

    @PostMapping("/api/update-with-fit")
    @ResponseBody
    public boolean updateRelicWithFit(@RequestBody RelicEntityVO vo) {
        RelicEntity relicEntity = convertService.toPO(vo);
        relicEntityService.updateRelicEntity(relicEntity);

        RelicFitQuery query = new RelicFitQuery();
        query.setRelicIdList(Collections.singletonList(relicEntity.getRelicEntityId()));
        relicFitService.deleteRelicFitByQuery(query);

        List<StarRailCharacter> characterList = convertService.toCharacterList(Collections.singletonList("all"));
        List<RelicEntity> relicEntityList = Collections.singletonList(relicEntity);
        List<RelicFit> relicFitList = relicCheckService.genRelicFit(characterList, relicEntityList);
        relicFitService.addRelicFitList(relicFitList);
        return true;
    }

    @PostMapping("/api/submit-with-fit")
    @ResponseBody
    public int submitRelicWithFit(@RequestBody RelicEntityVO vo) {
        RelicEntity relicEntity = convertService.toPO(vo);
        relicEntityService.insertRelicEntity(relicEntity);

        List<StarRailCharacter> characterList = convertService.toCharacterList(Collections.singletonList("all"));
        List<RelicEntity> relicEntityList = Collections.singletonList(relicEntity);
        List<RelicFit> relicFitList = relicCheckService.genRelicFit(characterList, relicEntityList);
        relicFitService.addRelicFitList(relicFitList);
        return relicEntity.getRelicEntityId();
    }
}
