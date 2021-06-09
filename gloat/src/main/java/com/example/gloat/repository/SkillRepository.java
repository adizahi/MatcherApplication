package com.example.gloat.repository;

import com.example.gloat.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> { //??
    Skill findByName(String name);
}
