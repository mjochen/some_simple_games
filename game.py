from typing import Dict, List, Union
from player import *
from card import ClueCards
from board import Board
import logging

import random

class Game:
    def __init__(self, humanplayers: List[str], computerplayers: List[str]) -> None:
        self._players = []
        self._hidden_cards = []
        self._next_up = 0

        self._players = []
        for name in humanplayers:
            self._players.append(HumanPlayer(name))
        for name in computerplayers:
            if name[-1] == "1":
                self._players.append(ComputerPlayer_1(name))
            else:
                self._players.append(ComputerPlayer(name))

        self._next_up = len(self._players) - 1
        # Hidden cards
        for t in ClueCards().get_card_types:
            self._hidden_cards.append(ClueCards().get_random_card(t))

        logging.debug(self._hidden_cards)

        card_list = ClueCards().clue_card_list()
        for h in self._hidden_cards:
            card_list.remove(h)

        # Deal cards
        i = 0
        while len(card_list) > 0:
            card = random.choice(card_list)
            self._players[i % len(self._players)].receive_card(card)
            card_list.remove(card)
            i+=1

        for p in self._players:
            logging.debug(p.name)
            logging.debug(p.get_all_cards)

    @property
    def get_next_up(self):
        self._next_up = (self._next_up+1) % len(self._players)
        return self._next_up

    def get_next_value(self, i: int):
        return (i+1) % len(self._players)


    def play_turn(self) -> dict:
        # next player
        i = self.get_next_up
        current_player = self._players[i]

        #move
        dice_throw = random.randint(1,6) + random.randint(1,6)
        ret = {
        "Playing": current_player.name,
        "Dice_throw": dice_throw,
        "Now_in": current_player.position,
        "Moving_to": current_player.move(dice_throw)
        }

        # ask cards
        asked_cards = current_player.ask_cards()
        ret["Asked_cards"] = asked_cards
        j = self.get_next_value(i)
        while True:
            if (shown_card := self._players[j].show_card(asked_cards)) != "":
                current_player.see_card(shown_card, self._players[j].name)
                ret["Responded"] = self._players[j].name
                break

            j = self.get_next_value(j)
            if j == i:
                current_player.see_card("", "")
                ret["Responded"] = "Nobody had those!"
                break



        if len(guess := current_player.guess()) == 3:
            if sorted(guess) == sorted(self._hidden_cards):
                logging.debug(f"{current_player.name} Won!")
                return {"won":current_player.name}
            else:
                logging.debug(f"{current_player.name} made a bad guess. Shame!")



        return ret
