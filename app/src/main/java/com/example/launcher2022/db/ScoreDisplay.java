package com.example.launcher2022.db;

public class ScoreDisplay {
    int ID;
    Integer score;
    String username;
    String datetime;
    Float duration;
    String country;
    String avatar;

    public ScoreDisplay(int ID, Integer score, String datetime, Float duration, String username, String avatar, String country) {
        this.ID = ID;
        this.score = score;
        this.datetime = datetime;
        this.duration = duration;
        this.username = username;
        this.country = country;
        this.avatar = avatar;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}