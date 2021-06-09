package com.example.gloat.model;

import javax.persistence.*;

@Entity
@Table(name = "SKILL")
public class Skill {
    @Id
    @Column(name = "id")
//    @GeneratedValue
//    private final int id;
    private int id; //??

    @Column(name = "name")
    private String name;

    public Skill(){}

    public Skill(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        if (name.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Skill [id=" + id + ", name=" + name + "]";
    }
}
