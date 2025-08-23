package com.example.qwixx.model;

// import com.example.qwixx.model.Row;

public class ScoreCard {
    private Row[] colors = new Row[4];
    private int penalties;

    public ScoreCard(boolean complex) {
        for (int i = 0; i < this.colors.length; i++) {
            this.colors[i] = new Row(complex,(i>=2));
        }
        this.penalties = 0;
    }

    public void addPenalty() {
        this.penalties++;
    }

    public boolean crossField(String color, int number) {
        int colorIndex = Translator.nameToIndex(color);

        if(colorIndex > 4) return false; // unexisting color returns index 1000
        return this.colors[colorIndex].crossNumber(number);
    }

    public boolean testField(String color, int number)
    {
        return this.colors[Translator.nameToIndex(color)].testNumber(number);
    }

    public boolean[] returnAllLockedRows()
    {
        boolean[] dice = new boolean[this.colors.length];
        for(int i=0;i<this.colors.length;i++)
        {
            dice[i] = this.colors[i].isLocked();
        }
        return dice;
    }

    public int getScore() {
        int total = 0;
        for (Row row : this.colors) 
            total += row.calculateScore();

        total -= 5 * this.penalties;
        return total;
    }

    public boolean getGameIsDone() {
        if(this.penalties>=4) return true;
        int total = 0;
        for(Row c: this.colors)
        {
            if(c.isLocked()) total += 1;
        }
        if(total >= 2) return true;

        return false;
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
