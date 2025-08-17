// src/main/java/com/example/qwixx/controller/GameController.java
package com.example.qwixx.controller;

import com.example.qwixx.model.DiceRoll;
import com.example.qwixx.model.GameState;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/game")
// @CrossOrigin(origins = "*") // allow frontend from elsewhere
public class GameController {

    private final Random random = new Random();
    private GameState game = new GameState();

    @GetMapping("/state")
    public GameState getState() {
        return game;
    }

    @PostMapping("/roll")
    public GameState rollDice() {
        Map<String, Integer> dice = new LinkedHashMap<>();
        dice.put("white1", random.nextInt(6) + 1);
        dice.put("white2", random.nextInt(6) + 1);
        dice.put("red",    random.nextInt(6) + 1);
        dice.put("yellow", random.nextInt(6) + 1);
        dice.put("green",  random.nextInt(6) + 1);
        dice.put("blue",   random.nextInt(6) + 1);

        // game.setCurrentRoll(new DiceRoll(dice));
        return game;
    }
}
