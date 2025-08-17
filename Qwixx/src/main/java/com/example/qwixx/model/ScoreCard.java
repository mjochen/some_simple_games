// src/main/java/com/example/qwixx/model/ScoreCard.java
package com.example.qwixx.model;

import java.util.*;

public class ScoreCard {
    private Map<String, List<Boolean>> rows;
    private int penalties;

    public ScoreCard() {
        rows = new LinkedHashMap<>();
        // Qwixx rows: red, yellow = 2→12 ; green, blue = 12→2
        rows.put("red",    initRow(2, 12, true));
        rows.put("yellow", initRow(2, 12, true));
        rows.put("green",  initRow(12, 2, false));
        rows.put("blue",   initRow(12, 2, false));
        penalties = 0;
    }

    private List<Boolean> initRow(int start, int end, boolean ascending) {
        int size = Math.abs(end - start) + 1;
        List<Boolean> marks = new ArrayList<>(Collections.nCopies(size, false));
        return marks;
    }

    public Map<String, List<Boolean>> getRows() {
        return rows;
    }

    public void setRows(Map<String, List<Boolean>> rows) {
        this.rows = rows;
    }

    public int getPenalties() {
        return penalties;
    }

    public void addPenalty() {
        this.penalties++;
    }
}
