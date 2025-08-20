package com.example.qwixx.model;

// import com.example.qwixx.model.Row;

public class ScoreCard {
    private Row[] colors = new Row[4];
    private int penalties;

    public ScoreCard(boolean complex) {
        for(int i=0; i<this.colors.length; i++)
        {
            this.colors[i] = new Row(complex);
        }
        this.penalties = 0;
    }

    
    public void addPenalty() {
        this.penalties++;
    }

    public void crossField(String color, int number)
    {
        this.colors[Translator.nameToIndex(color)].crossNumber(number);
    }
    
    public Row getYellow() {
        return colors[Translator.nameToIndex("yellow")];
    }


    public Row getRed() {
        return colors[Translator.nameToIndex("red")];
    }


    public Row getGreen() {
        return colors[Translator.nameToIndex("green")];
    }


    public Row getBlue() {
        return colors[Translator.nameToIndex("blue")];
    }


    public int getPenalties() {
        return penalties;
    }



    
}
