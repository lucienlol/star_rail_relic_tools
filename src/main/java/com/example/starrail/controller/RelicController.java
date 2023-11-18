package com.example.starrail.controller;

import com.example.starrail.po.RelicEntity;
import com.example.starrail.service.ConvertService;
import com.example.starrail.service.RelicEntityService;
import com.example.starrail.vo.RelicEntityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("relic")
public class RelicController {

    @Autowired
    RelicEntityService relicEntityService;

    @Autowired
    ConvertService convertService;

    @GetMapping("/api/{id}")
    @ResponseBody
    public RelicEntityVO getRelicEntity(@PathVariable Integer id) {
        return convertService.toVO(relicEntityService.getById(id));
    }

    @PostMapping("/api/add")
    @ResponseBody
    public void addRelicEntity(@RequestBody RelicEntityVO vo) {
        relicEntityService.insertRelicEntity(convertService.toPO(vo));
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
}
