from typing import List
from player import HumanPlayer, ComputerPlayer
from card import ClueCards
from board import Board
import logging

import random

class Game:
    _players = []
    _hidden_cards = []
    _next_up = 0

    def __init__(self, humanplayers: List[str], computerplayers: List[str]) -> None:
        self._players = []
        for name in humanplayers:
            self._players.append(HumanPlayer(name))
        for name in computerplayers:
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

    def get_next_up(self):
        self._next_up = (self._next_up+1) % len(self._players)
        return self._next_up

    def play_turn(self) -> bool:

        # wie aan de beurt?

        # gooien
        # naar kamer gaan
        # kaarten vragen

        # weet speler het?


        return False
