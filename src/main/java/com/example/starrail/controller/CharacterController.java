package com.example.starrail.controller;

import com.example.starrail.po.StarRailCharacter;
import com.example.starrail.service.CacheService;
import com.example.starrail.service.CharacterBuildService;
import com.example.starrail.service.CharacterService;
import com.example.starrail.vo.CharacterBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("character")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @Autowired
    CacheService cacheService;

    @Autowired
    CharacterBuildService characterBuildService;

    @GetMapping("/all")
    @ResponseBody
    public List<StarRailCharacter> getAllCharacter() {
        return characterService.getAll();
    }

    @GetMapping("/all-show")
    @ResponseBody
    public List<StarRailCharacter> getAllShow() {
        return characterService.getAllShow();
    }

    @PostMapping("/add")
    public void addCharacter(@RequestBody StarRailCharacter character) {
        characterService.addCharacter(character);
        cacheService.charNeedUpdate();
    }

    @PostMapping("/update")
    public void updateCharacter(@RequestBody StarRailCharacter character) {
        characterService.addCharacter(character);
        cacheService.charNeedUpdate();
    }

    @GetMapping("/list")
    public String characterList() {
        return "html/character-list";
    }

    @GetMapping("/{id}")
    public String characterBuild(@PathVariable Integer id) {
        return "html/character-build";
    }

    @GetMapping("/new")
    public String characterNewBuild() {
        return "html/character-build";
    }

    @GetMapping("/build/{id}")
    @ResponseBody
    public CharacterBuild getCharacterBuild(@PathVariable Integer id) {
        return characterBuildService.getCharacterBuild(id);
    }

    @PostMapping("/build/add")
    @ResponseBody
    public void addCharacterBuild(@RequestBody CharacterBuild characterBuild) {
        characterBuildService.addCharacterBuild(characterBuild);
    }

    @PostMapping("/build/update")
    @ResponseBody
    public void updateCharacterBuild(@RequestBody CharacterBuild characterBuild) {
        characterBuildService.updateCharacterBuild(characterBuild);
    }

}
