// src/main/java/com/example/qwixx/model/GameState.java
package com.example.qwixx.model;

public class GameState {
    private ScoreCard scoreCard;
    private DiceRoll currentRoll;

    public GameState() {
        this.scoreCard = new ScoreCard();
        this.currentRoll = null; // no roll yet
    }

    public ScoreCard getScoreCard() {
        return scoreCard;
    }

    public void setScoreCard(ScoreCard scoreCard) {
        this.scoreCard = scoreCard;
    }

    public DiceRoll getCurrentRoll() {
        return currentRoll;
    }

    public void setCurrentRoll(DiceRoll currentRoll) {
        this.currentRoll = currentRoll;
    }
}
