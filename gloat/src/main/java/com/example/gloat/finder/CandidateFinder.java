package com.example.gloat.finder;

import com.example.gloat.model.Candidate;
import com.example.gloat.model.Job;
import com.example.gloat.model.Skill;
import com.example.gloat.repository.CandidateRepository;
import com.example.gloat.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class CandidateFinder {

    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    SkillRepository skillRepository;
    @Autowired
    SkillFinder skillFinder;

    public ResponseEntity<Candidate> createCandidate(Candidate candidate) {
        try {
            Candidate candidate1 = candidateRepository.save(candidate);
            return new ResponseEntity<>(candidate1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Candidate> findCandidate(Job job) {
        try {
            Skill skill = skillRepository.findByName(job.getSkill());
            if (skill==null){
                //Consideration: job with un-familiar skill can be ignore
                System.out.println("unfamiliar skill. ignore job search candidate request");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Candidate candidateBySkill = candidateRepository.findAllBySkills(skill)
                    .stream()
                    .filter(filterCandidatesByJobTitle(job.getTitle()))
                    .findFirst()
                    .orElse(null);

            if (candidateBySkill!=null){
                System.out.println("found a match candidate with match skill. " +
                                "candidate:[" + candidateBySkill + "] , job: [" + job + "]");
                return new ResponseEntity<>(candidateBySkill, HttpStatus.FOUND);
            }

            Candidate candidateByTitle = candidateRepository.findAllByTitle(job.getTitle())
                    .stream()
                    .findFirst()
                    .orElse(null);

            if (candidateByTitle!=null){
                System.out.println("found match candidate with no match skills: " +
                        "candidate:[" + candidateByTitle + "] , job: [" + job + "]");
                return new ResponseEntity<>(candidateByTitle, HttpStatus.FOUND);
            }

            System.out.println("cannot find match candidate for your job : [" + job + "]");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Predicate<Candidate> filterCandidatesByJobTitle(String jobTitle) {
        //Consideration: case of "developer" or "engineer" are the only possible duplicate match.
        boolean haveAnotherOptionForMatch = false;

        if (jobTitle.contains("developer") || jobTitle.contains("engineer"))
            haveAnotherOptionForMatch = true;

        if (haveAnotherOptionForMatch){
            return candidate -> candidate.getTitle().contains(jobTitle.replace("developer","engineer"))
                    || candidate.getTitle().contains(jobTitle.replace("engineer","developer"));
        }
        return candidate -> candidate.getTitle().contains(jobTitle);
    }

}
