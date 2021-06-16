package com.example.gloat.repository;

import com.example.gloat.model.Candidate;
import com.example.gloat.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    List<Candidate> findAllByTitle(String title);
    List<Candidate> findAllBySkills(Skill skill);
}