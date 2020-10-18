package com.gmul.toy.example.domain;

public class Score {

    private int strikeScore;
    private int ballScore;

    public Score(int ballScore, int strikeScore) {
        this.ballScore = ballScore;
        this.strikeScore = strikeScore;
    }

    public int strikes() {
        return strikeScore;
    }

    public int balls() {
        return ballScore;
    }
}
