package com.example.starrail.controller;

import com.example.starrail.po.Stat;
import com.example.starrail.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("stat")
public class StatController {

    @Autowired
    StatService statService;

    @GetMapping("/all")
    @ResponseBody
    public List<Stat> getAllStats() {
        return statService.getAllStats();
    }

    @GetMapping("/main-stats")
    @ResponseBody
    public List<Stat> getAllMainStats() {
        return statService.getAllMainStats();
    }

    @GetMapping("/body-stats")
    @ResponseBody
    public List<Stat> getAllBodyStats() {
        return statService.getAllBodyStats();
    }

    @GetMapping("/feet-stats")
    @ResponseBody
    public List<Stat> getAllFeetStats() {
        return statService.getAllFeetStats();
    }

    @GetMapping("/sphere-stats")
    @ResponseBody
    public List<Stat> getAllSphereStats() {
        return statService.getAllSphereStats();
    }

    @GetMapping("/rope-stats")
    @ResponseBody
    public List<Stat> getAllRopeStats() {
        return statService.getAllRopeStats();
    }

    @GetMapping("/sub-stats")
    @ResponseBody
    public List<Stat> getAllSubStats() {
        return statService.getAllSubStats();
    }
}
