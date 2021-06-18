package com.example.gloat.finder;

import com.example.gloat.model.Candidate;
import com.example.gloat.model.Job;
import com.example.gloat.model.Skill;
import com.example.gloat.repository.CandidateRepository;
import com.example.gloat.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Slf4j
@Service
public class CandidateFinder {

    private final String DEVELOPER = "developer";
    private final String ENGINEER = "engineer";

    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    SkillRepository skillRepository;

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
            Optional<Skill> skill = skillRepository.findByName(job.getSkill());
            if (skill.isPresent())
                return findCandidateBySkillAndTitle(job, skill.get());
            return findCandidateByTitle(job);

        } catch (Exception e) {
            log.error("internal_server_error for job : [{}]", job);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Candidate> findCandidateByTitle(Job job) {
        Optional<Candidate> candidateByTitle = getCandidateByTitle(job);

        if (candidateByTitle.isPresent()){
            log.info("found match candidate with no match skills. candidate:[{}] , job: [{}]", candidateByTitle.get(), job);
            return new ResponseEntity<>(candidateByTitle.get(), HttpStatus.OK);
        }

        log.info("cannot find match candidate for your job : [{}]", job);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Candidate> findCandidateBySkillAndTitle(Job job, Skill skill) {
        Optional<Candidate> candidateBySkill = getCandidateBySkillAndTitle(job, skill);
        if (candidateBySkill.isPresent()){
            log.info("found a match candidate with match skill and title. candidate:[{}] , job: [{}]", candidateBySkill.get(), job);
            return new ResponseEntity<>(candidateBySkill.get(), HttpStatus.OK);
        }

        //if no skill match found, we search for match by title with no skill
        Optional<Candidate> candidateByTitle = getCandidateByTitle(job);
        if (candidateByTitle.isPresent()){
            log.info("found match candidate by title with no match skills.  candidate:[{}] , job: [{}]", candidateByTitle.get(), job);
            return new ResponseEntity<>(candidateByTitle.get(), HttpStatus.OK);
        }

        log.info("cannot find match candidate for your job : [{}]", job);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    private Optional<Candidate> getCandidateByTitle(Job job) {
        // if job.title combine from "developer" / "engineer" then find both options
        if (job.getTitle().contains(DEVELOPER) || job.getTitle().contains(ENGINEER)){
            List<String> titles = new ArrayList<>();
            titles.add(job.getTitle().replace(DEVELOPER,ENGINEER));
            titles.add(job.getTitle().replace(ENGINEER,DEVELOPER));

            return candidateRepository.findByTitleIn(titles)
                    .stream()
                    .findFirst();
        }
        //else regular search by title
        return candidateRepository.findAllByTitle(job.getTitle())
                .stream()
                .findFirst();
    }

    private Optional<Candidate> getCandidateBySkillAndTitle(Job job, Skill skill) {
        return Optional.ofNullable(candidateRepository.findAllBySkills(skill)
                .stream()
                .filter(filterCandidatesByJobTitle(job.getTitle()))
                .findFirst()
                .orElse(null));
    }

    public Predicate<Candidate> filterCandidatesByJobTitle(String jobTitle) {
        //Consideration: case of "developer" or "engineer" are the only possible duplicate match.
        boolean haveAnotherOptionForMatch = false;

        if (jobTitle.contains(DEVELOPER) || jobTitle.contains(ENGINEER))
            haveAnotherOptionForMatch = true;

        if (haveAnotherOptionForMatch){
            return candidate -> candidate.getTitle().contains(jobTitle.replace(DEVELOPER,ENGINEER))
                    || candidate.getTitle().contains(jobTitle.replace(ENGINEER,DEVELOPER));
        }
        return candidate -> candidate.getTitle().contains(jobTitle);
    }

}
