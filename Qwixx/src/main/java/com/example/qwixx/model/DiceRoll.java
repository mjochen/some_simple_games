package com.example.qwixx.model;

import java.util.Random;

public class DiceRoll {
    private final Random random = new Random();
    private boolean[] activeDice = {true, true, true, true};
    private int[] currentRoll = {0,0,0,0,0,0};

    public DiceRoll()
    {
    }

    public void roll()
    {
        this.currentRoll[0] = random.nextInt(6) + 1;
        this.currentRoll[1] = random.nextInt(6) + 1;
        for(int i=0; i < this.activeDice.length; i++)
        {
            if(this.activeDice[i]) this.currentRoll[i+2] = random.nextInt(6) + 1;
            else this.currentRoll[i+2] = 0;
        }
    }

    // public int[] getDiceRoll()
    // {
    //     return this.currentRoll;
    // }

    public void deactivate(String color)
    {
        this.activeDice[Translator.nameToIndex(color)] = false;
    }
    
    public boolean[] currentActiveDice()
    {
        return this.activeDice;
    }

    public int getWhite1()
    {return this.currentRoll[0];}
    public int getWhite2()
    {return this.currentRoll[1];}
    public int getYellow()
    {return this.currentRoll[2+Translator.nameToIndex("yellow")];}
    public int getRed()
    {return this.currentRoll[2+Translator.nameToIndex("red")];}
    public int getGreen()
    {return this.currentRoll[2+Translator.nameToIndex("green")];}
    public int getBlue()
    {return this.currentRoll[2+Translator.nameToIndex("blue")];}
}
