package com.example.gloat.controller;

import com.example.gloat.handler.SkillHandler;
import com.example.gloat.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SkillController {

    @Autowired
    SkillHandler skillHandler;

    @GetMapping("/skills/{skillName}")
    public ResponseEntity<Skill> getSkillByName(@PathVariable String skillName) {
        return skillHandler.getSkillByName(skillName);
    }

    @PostMapping("/skill")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        return skillHandler.createSkill(skill);
    }

    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getAllSkills() {
        return skillHandler.getAllSkills();
    }

//    @GetMapping("/{skillId}")
//    public ResponseEntity<Skill> getSkillById(@PathVariable Integer skillId) {
//        return skillHandler.getSkillById(skillId);
//    }

}
