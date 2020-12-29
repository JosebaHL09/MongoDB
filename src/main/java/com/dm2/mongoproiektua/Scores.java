/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dm2.mongoproiektua;

/**
 *
 * @author hernandez.joseba
 */
public class Scores {
    private double score;
    private String type;

    public Scores() {
       
    }
    public Scores(double score, String type) {
        this.score = score;
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return  "[Score: " + score + ", Type: " + type +']';
    }
}
