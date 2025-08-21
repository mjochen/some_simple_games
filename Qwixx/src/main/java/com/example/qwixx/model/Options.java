package com.example.qwixx.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Options {
    private String[] opts;

    public Options(boolean white, DiceRoll dice, ScoreCard sc) {
        ArrayList<String> temp_opts = new ArrayList<String>();

        if (white) {
            int nr = dice.getWhite1() + dice.getWhite2();
            for (String color : Translator.allColors()) {
                if (sc.testField(color, nr))
                    temp_opts.add(color + " " + nr);
            }
        } else {
            int[] colored = dice.theColoredDiceGet();
            int nr;
            for (int i = 0; i < colored.length; i++) {
                nr = colored[i] + dice.getWhite1();
                if (sc.testField(Translator.indexToName(i), nr))
                    temp_opts.add(Translator.indexToName(i) + " " + nr);
                nr = colored[i] + dice.getWhite2();
                if (sc.testField(Translator.indexToName(i), nr))
                    temp_opts.add(Translator.indexToName(i) + " " + nr);
            }
        }

        // this.opts = new String[] {"blue 2", "blue 3", "blue 4", "blue 5", "blue 6",
        // "blue 7", "blue 8", "blue 9", "blue 10", "blue 11", "blue 12"};
        List<String> temp_opts_no_dup = new ArrayList<String>(new HashSet<String>(temp_opts));
        this.opts = new String[temp_opts_no_dup.size()];
        this.opts = temp_opts_no_dup.toArray(this.opts);
    }

    public String[] getOptions() {
        return this.opts;
    }
}
