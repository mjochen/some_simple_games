from typing import Dict, List, Union
from player import *

class Game:

    def __init__(self, humanplayers: List[str], computerplayers: List[str]) -> None:
        self._players = []
        self._next = 0

        for name in humanplayers:
            self._players.append(HumanPlayer(name))
        for name in computerplayers:
            pass

    def get_nr_remaining_players(self) -> int:
        total = 0
        for p in self._players:
            if not p.is_dead:
                total += 1

        return total

    def turn(self) -> bool:
        all_hands = dict()

        for i, p in enumerate(self._players):
            if i != self._next and not p.is_dead:
                all_hands[str(i)] = p.get_hands

        print(all_hands)

        todo = self._players[self._next].do_tap(all_hands)
        print(todo)
        self._players[todo[0]].get_tapped(todo[1], todo[2])
        self.get_next()


    def get_next(self) -> int:
        print(self._next)
        self._next += 1
        self._next %= len(self._players)
        if self._players[self._next].is_dead:
            self.get_next()