package com.example.qwixx.model;

public class Options {
    private String[] opts;
    
    public Options(boolean white, DiceRoll dice, ScoreCard sc)
    {
        this.opts = new String[] {"blue 2", "green 3"};
    }

    public String[] getOptions()
    {
        return this.opts;
    }
}
