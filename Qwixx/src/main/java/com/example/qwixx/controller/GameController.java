// src/main/java/com/example/qwixx/controller/GameController.java
package com.example.qwixx.controller;

import com.example.qwixx.model.DiceRoll;
import com.example.qwixx.model.ScoreCard;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
// @CrossOrigin(origins = "*") // allow frontend from elsewhere
public class GameController {

    private DiceRoll dice = new DiceRoll();
    private ScoreCard card = new ScoreCard(false);;

    @GetMapping("/scorecard")
    public ScoreCard getScoreCard() {
        this.card.crossField("yellow", 8);
        this.card.crossField("red", 3);
        return this.card;
    }

    @GetMapping("/roll")
    public DiceRoll rollDice() {
        return dice;
    }

    @GetMapping("/deactivate")
    public DiceRoll deactivate() {
        dice.deactivate("blue");
        dice.roll();
        return dice;
    }
}
