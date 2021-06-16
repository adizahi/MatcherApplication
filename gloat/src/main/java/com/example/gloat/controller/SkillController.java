package com.example.gloat.controller;

import com.example.gloat.finder.SkillFinder;
import com.example.gloat.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillController {

    @Autowired
    SkillFinder skillFinder;

    @GetMapping("/skills/{skillName}")
    public ResponseEntity<Skill> getSkillByName(@PathVariable String skillName) {
        return skillFinder.getSkillByName(skillName);
    }

    //TODO: need to be fix
//    @PostMapping("/skill")
//    public ResponseEntity<Skill> createSkill(@RequestBody String skillName) {
//        return skillFinder.createSkill(skillName);
//    }

    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getAllSkills() {
        return skillFinder.getAllSkills();
    }

//    @GetMapping("/{skillId}")
//    public Skill getSkillById(@PathVariable Integer skillId) {
//        return skillHandler.getSkillById(skillId);
//    }


}
