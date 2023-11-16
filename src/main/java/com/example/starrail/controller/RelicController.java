package com.example.starrail.controller;

import com.example.starrail.po.RelicEntity;
import com.example.starrail.service.ConvertService;
import com.example.starrail.service.RelicEntityService;
import com.example.starrail.vo.RelicEntityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("relic")
public class RelicController {

    @Autowired
    RelicEntityService relicEntityService;

    @Autowired
    ConvertService convertService;

    @GetMapping("/api/{id}")
    @ResponseBody
    public RelicEntity getRelicEntity(@PathVariable Integer id) {
        return relicEntityService.getById(id);
    }

    @PostMapping("/api/add")
    @ResponseBody
    public void addRelicEntity(@RequestBody RelicEntityVO vo) {
        relicEntityService.insertRelicEntity(convertService.toPO(vo));
    }

    @GetMapping("/api/all")
    @ResponseBody
    public List<RelicEntity> getRelicEntityList() {
        return relicEntityService.getAll();
    }
}
