package com.example.qwixx.model;

import java.util.Random;

public class DiceRoll {
    private final Random random = new Random();
    private boolean[] activeDice = {true, true, true, true};
    private int[] currentRoll = {0,0,0,0,0,0};

    public DiceRoll()
    {
    }

    public int[] newDiceRoll()
    {
        this.currentRoll[0] = random.nextInt(6) + 1;
        this.currentRoll[1] = random.nextInt(6) + 1;
        for(int i=0; i < this.activeDice.length; i++)
        {
            if(this.activeDice[i]) this.currentRoll[i+2] = random.nextInt(6) + 1;
            else this.currentRoll[i+2] = 0;
        }

        return this.currentRoll;
    }

    public int[] getDiceRoll()
    {
        return this.currentRoll;
    }

    public void deactivate(String color)
    {
        if(color.equals("red")) this.activeDice[0] = false;
        if(color.equals("yellow")) this.activeDice[1] = false;
        if(color.equals("green")) this.activeDice[2] = false;
        if(color.equals("blue")) this.activeDice[3] = false;
    }
    
    public boolean[] getActiveDice()
    {
        return this.activeDice;
    }
}
