from typing import List
from player import HumanPlayer, ComputerPlayer
from card import ClueCards
import random

class Game:
    _players = []
    _hidden_cards = []

    def __init__(self, humanplayers: List[str], computerplayers: List[str]) -> None:
        self._players = []
        for name in humanplayers:
            self._players.append(HumanPlayer(name))
        for name in computerplayers:
            self._players.append(ComputerPlayer(name))

        # Hidden cards
        card_list = ClueCards().cue_card_list
        self._hidden_cards.append(random.choice(card_list))

        next_card = random.choice(card_list)  
        while next_card[:4] == self._hidden_cards[0][:4]:
            next_card = random.choice(card_list)  
        self._hidden_cards.append(next_card)

        next_card = random.choice(card_list)  
        while next_card[:4] == self._hidden_cards[0][:4] or \
                next_card[:4] == self._hidden_cards[1][:4]:
            next_card = random.choice(card_list)  
        self._hidden_cards.append(next_card)

        for c in self._hidden_cards:
            card_list.remove(c)

        #TODO: logprint
        print(self._hidden_cards)

        # Deal cards
        i = 0
        while len(card_list) > 0:
            card = random.choice(card_list)
            self._players[i % len(self._players)].receive_card(card)
            card_list.remove(card)
            i+=1

        #TODO: logprint
        for p in self._players:
            p.print_all_cards()

 