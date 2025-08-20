package com.example.qwixx.model;

import java.util.Random;

public class Row {
    private int[] numbers = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
    private boolean[] ticked = { false, false, false, false, false, false, false, false, false, false, false };
    private boolean locked;
    private Random rnd = new Random();

    // constructor
    public Row(boolean complex) {
        if (complex) {
            this.shuffleArray(this.numbers);
        }
        this.locked = false;
    }

    public void crossNumber(int number)
    {
        for(int i=0; i< this.numbers.length; i++)
        {
            if(this.numbers[i] == number) this.ticked[i] = true;
        }
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
