package com.example.starrail.controller;

import com.example.starrail.service.CacheService;
import com.example.starrail.service.RelicCheckService;
import com.example.starrail.vo.CharacterCheckVO;
import com.example.starrail.vo.RelicEntityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Thread.sleep;

@Controller
@RequestMapping("relic-check")
public class RelicCheckController {

    @Autowired
    CacheService cacheService;

    @Autowired
    RelicCheckService relicCheckService;

    @GetMapping("/home")
    public String relicSetPage() {
        return "html/relic-check";
    }

    @GetMapping("/refresh")
    @ResponseBody
    public Boolean refresh()  {
        relicCheckService.prepare();
        return Boolean.TRUE;
    }

    @PostMapping("/check")
    @ResponseBody
    public List<CharacterCheckVO> doCheck(@RequestBody RelicEntityVO relicEntityVO) {
        return relicCheckService.doCheck(relicEntityVO);
    }
}
