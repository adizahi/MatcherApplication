package com.example.gloat.model;

import lombok.Data;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "Skill")
public class Skill {

    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    //Consideration: skill.name first letter always in lower case
    @Column(name = "name")
    private String name;

    public Skill(String name) {
        this.name = name;
    }

    public Skill() {
    }
}
