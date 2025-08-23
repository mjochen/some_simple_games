package com.example.qwixx.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Row {
    private int[] numbers = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
    private int[] points = { 0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78 };
    private boolean[] ticked = { false, false, false, false, false, false, false, false, false, false, false };
    private boolean locked;
    private Random rnd = new Random();

    // constructor
    public Row(boolean complex, boolean reverse) {
        if (complex) {
            this.shuffleArray(this.numbers);
        }
        if (reverse) {
            int[] temp = new int[this.numbers.length];
            int j =this.numbers.length;
            for (int i = 0; i < this.numbers.length; i++) {
                temp[j - 1] = this.numbers[i];
                j = j - 1;
            }
            this.numbers = temp;
        }
        this.locked = false;
    }

    public boolean crossNumber(int number) {
        if (this.testNumber(number)) {
            for (int i = this.numbers.length - 1; i >= 0; i--) {
                if (this.numbers[i] == number) {
                    this.ticked[i] = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean testNumber(int number) {
        if (this.numbers[10] == number) {
            int tickCount = 0;
            for (int i = 0; i < this.ticked.length; i++) {
                if (this.ticked[i])
                    tickCount++;
            }
            if (tickCount > 5) {
                // this.ticked[10] = true;
                this.locked = true;
                return true;
            } else {
                return false;
            }
        }
        for (int i = this.numbers.length - 1; i >= 0; i--) {
            if (this.ticked[i]) {
                return false;
            }
            if (this.numbers[i] == number) {
                // this.ticked[i] = true;
                return true;
            }
        }
        return false;
    }

    public int calculateScore() {
        int total = 0;
        for (boolean tick : this.ticked)
            if (tick)
                total++;
        if (this.locked)
            total++;
        return this.points[total];
    }

    public int[] getNumbers() {
        return numbers;
    }

    public boolean[] getTicked() {
        return ticked;
    }

    public boolean isLocked() {
        return locked;
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
