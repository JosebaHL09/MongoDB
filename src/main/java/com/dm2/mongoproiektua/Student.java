/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dm2.mongoproiektua;

import java.util.List;

/**
 *
 * @author hernandez.joseba
 */
public class Student {

    private int id;
    private String name;
    private List<Scores> scores;
    
    public Student() {
        
    }
    public Student(int id, String name, List<Scores> scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Scores> getScores() {
        return scores;
    }

    public void setScores(List<Scores> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "===========================\n"
                + "*ID: " + id + " - Name: " + name + "\n\t Results " + scores+
                "\n===========================";
    }

}
