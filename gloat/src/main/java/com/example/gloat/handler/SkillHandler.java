package com.example.gloat.handler;

import com.example.gloat.model.Skill;
import com.example.gloat.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillHandler {

    @Autowired
    SkillRepository skillRepository;

    public ResponseEntity<List<Skill>> getAllSkills() {
        try {
            List<Skill> skills = new ArrayList<>();
            skills.addAll(skillRepository.findAll());

            if (skills.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(skills, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Skill> getSkillByName(String skillName){
        try {
            Skill skill = skillRepository.findByName(skillName);
            if (skill.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(skill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Skill> createSkill(Skill skill) {
        try {
            Skill skill1 = skillRepository.save(new Skill(skill.getId(), skill.getName()));
            return new ResponseEntity<>(skill1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    public ResponseEntity<Skill> getSkillById(int skillId){
//        try {
//            Skill skill = skillRepository.getOne(skillId);
//            if (skill.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(skill, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
