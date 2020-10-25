package com.gmul.toy.example.domain;

import java.util.Random;

public class Game {
    private int[] nums;

    public Game() {
        Random random = new Random();
        int firstNo = random.nextInt(10);
        int secondNo = random.nextInt(10);
        int thirdNo = random.nextInt(10);

        this.nums = new int[] {firstNo, secondNo, thirdNo};
    }

    // 값을 주입 받거나
    public Game(int[] nums) {
        this.nums = nums;
    }
}
