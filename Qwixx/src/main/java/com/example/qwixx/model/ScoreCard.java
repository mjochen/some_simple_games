package com.example.qwixx.model;

import java.util.Arrays;
import java.util.Random;

public class ScoreCard {
    private int[] yellow, red, green, blue;
    private boolean[] yellow_ticked, red_ticked, green_ticked, blue_ticked;
    private boolean[] locked = { false, false, false, false };
    private int penalties;
    private Random rnd = new Random();

    public ScoreCard(boolean complex) {
        int[] basic = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        this.yellow = basic.clone();
        this.red = basic.clone();
        this.green = basic.clone();
        this.blue = basic.clone();
        if (complex) {
            this.shuffleArray(this.yellow);
            this.shuffleArray(this.red);
            this.shuffleArray(this.green);
            this.shuffleArray(this.blue);
        }

        boolean[] basic_ticked = new boolean[basic.length];
        Arrays.fill(basic_ticked, false);

        this.yellow_ticked = basic_ticked.clone();
        this.red_ticked = basic_ticked.clone();
        this.green_ticked = basic_ticked.clone();
        this.blue_ticked = basic_ticked.clone();

        this.penalties = 0;
    }

    public boolean[] getYellow_ticked() {
        return yellow_ticked;
    }

    public boolean[] getRed_ticked() {
        return red_ticked;
    }

    public boolean[] getGreen_ticked() {
        return green_ticked;
    }

    public boolean[] getBlue_ticked() {
        return blue_ticked;
    }

    public int getPenalties() {
        return penalties;
    }

    public int[] getYellow() {
        return yellow;
    }

    public int[] getRed() {
        return red;
    }

    public int[] getGreen() {
        return green;
    }

    public int[] getBlue() {
        return this.blue;
    }

    public boolean[] getLocked() {
        return locked;
    }

    public void addPenalty() {
        this.penalties++;
    }

    // https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private void shuffleArray(int[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
