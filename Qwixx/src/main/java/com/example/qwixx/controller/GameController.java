// src/main/java/com/example/qwixx/controller/GameController.java
package com.example.qwixx.controller;

import com.example.qwixx.model.DiceRoll;
import com.example.qwixx.model.ScoreCard;
import com.example.qwixx.model.Options;

import jakarta.servlet.http.HttpSession;

// import java.lang.StackWalker.Option;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
// @CrossOrigin(origins = "*") // allow frontend from elsewhere
public class GameController {

    private DiceRoll dice = new DiceRoll();
    private DiceRoll zero = new DiceRoll();
    private boolean canRoll = false;
    private int numberOptionsSkipped = 0;
    // private ScoreCard card = new ScoreCard(false);

    @PostMapping("/newGame")
    public ScoreCard newGame(@RequestParam boolean complex, HttpSession session) {
        ScoreCard sc = new ScoreCard(complex);
        session.setAttribute("scorecard", sc);
        this.canRoll = true;
        return sc;
    }

    @GetMapping("/roll")
    public DiceRoll rollDice(HttpSession session) {
        ScoreCard sc = (ScoreCard) session.getAttribute("scorecard");
        if (sc == null)
            return zero;
        if (!this.canRoll)
            return zero;
        if (sc.getGameIsDone())
            return zero;

        this.canRoll = false;
        // dice.roll();

        // always at least one option
        for (int i = 0; i <= 100; i++) {
            dice.roll();
            Options opt_w = new Options(true, dice, sc);
            Options opt_c = new Options(false, dice, sc);
            int l = opt_w.getOptions().length + opt_c.getOptions().length;
            if (l > 0)
                return dice;
        }

        return dice;
    }

    @PostMapping("/getOptions")
    public Options getOptions(@RequestParam boolean white, HttpSession session) {
        ScoreCard sc = (ScoreCard) session.getAttribute("scorecard");
        Options opt = new Options(white, dice, sc);
        return opt;
    }

    @PostMapping("/setOption")
    public ScoreCard setOption(@RequestParam String color, @RequestParam int number, @RequestParam boolean white,
            HttpSession session) {
        ScoreCard sc = (ScoreCard) session.getAttribute("scorecard");

        if (!sc.crossField(color, number))
            this.numberOptionsSkipped++;

        if (this.numberOptionsSkipped == 2)
            sc.addPenalty();

        if (!white) {
            this.canRoll = true;
            this.numberOptionsSkipped = 0;
        }

        this.dice.deactivateAlLotAtOnce(sc.returnAllLockedRows());

        return sc;
    }

    @GetMapping("/deactivate")
    public DiceRoll deactivate() {
        dice.deactivate("blue");
        dice.roll();
        return dice;
    }
}
