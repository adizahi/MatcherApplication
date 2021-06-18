package com.example.gloat.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.annotations.FetchMode.SELECT;

@Data
@Entity
@Table(name = "Candidate")
public class Candidate {

    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int candidate_id;

    //Consideration: candidate.title always in lower case
    @Column(name = "title")
    private String title;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
    @JoinTable(
            name = "Candidate_Skill",
            joinColumns = { @JoinColumn(name = "candidate_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    Set<Skill> skills = new HashSet<>();

    public Candidate(String title) {
        this.title = title;
    }

    public Candidate() {
    }
}
