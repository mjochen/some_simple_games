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

    @GetMapping("/scorecard")
    public ScoreCard getScoreCard() {
        ScoreCard card = new ScoreCard(false);
        return card;
    }

    @GetMapping("/roll")
    public int[] rollDice() {
        
        return dice.newDiceRoll();
    }

    @GetMapping("/deactivate")
    public int[] deactivate() {
        dice.deactivate("blue");
        return dice.newDiceRoll();
    }
}
