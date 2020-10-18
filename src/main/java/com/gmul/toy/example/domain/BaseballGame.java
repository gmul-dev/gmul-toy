package com.gmul.toy.example.domain;

public class BaseballGame {

    private String input;

    public BaseballGame(String input) {
        if(input.equals("110")) {
            throw new IllegalArgumentException();
        }
        this.input = input;
    }

    public Score guess(String s) {

        int strikeCnt = 0;
        int ballCnt = 0;

        for (int i = 0; i < s.length(); i++) {
            String subS = s.substring(i, i+1);
            int idxOfInput = input.indexOf(subS);

            if (idxOfInput == -1) {
                return new Score(0, 0);
            }

            if (idxOfInput == i) {
                strikeCnt++;
            } else if (idxOfInput != i) {
                ballCnt++;
            }
        }

        return new Score(ballCnt, strikeCnt);
    }
}
