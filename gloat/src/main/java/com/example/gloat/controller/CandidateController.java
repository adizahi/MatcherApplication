package com.example.gloat.controller;

import com.example.gloat.finder.CandidateFinder;
import com.example.gloat.model.Candidate;
import com.example.gloat.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidateController {

    @Autowired
    CandidateFinder candidateFinder;

    @PostMapping("/candidate")
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate) {
        return candidateFinder.createCandidate(candidate);
    }

    @PostMapping("/job")
    @ResponseBody
    public ResponseEntity<Candidate> findCandidateByJob(@RequestBody Job job) {
        return candidateFinder.findCandidate(job);
    }

    @GetMapping("/candidates")
    public List<Candidate> findAllCandidates() {
        return candidateFinder.findAllCandidates();
    }
}
