import logging
from typing import Dict, List, Union
from abc import ABC, abstractmethod

class Player(ABC):

    def __init__(self, name: str) -> None:
        self._fingers = { "left": 1, "right": 1}
        self._name = name

    def get_tapped(self, hand: str, nr_of_fingers: int) -> None:
        if hand in self._fingers.keys():
            if self._fingers[hand] == 0:
                raise ValueError(f"Hand {hand} is dood!")    
            else:
                self._fingers[hand] += nr_of_fingers
                self._fingers[hand] %= 5
        else:
            raise ValueError(f"Hand {hand} bestaat niet!")

    @property
    def can_split(self) -> bool:
        return 0 in self._fingers.values() and sum(self._fingers.values()) % 2 == 0

    @property
    def is_dead(self) -> bool:
        return sum(self._fingers.values()) == 0

    def split(self) -> None:
        if self.can_split:
            nr = sum(self._fingers.values()) // 2
            self._fingers = { "left": nr, "right": nr}

    @property
    def get_hands(self) -> Dict:
        return self._fingers

    @abstractmethod
    def do_tap(self) -> List:
        pass

    def to_left_right(self, choice: str) -> str:
        if choice[0].upper() == "L":
            return "left"
        else:
            return "right"


class HumanPlayer(Player):
    
    def __init__(self, name):
        super().__init__(name)
        print(f"Welkom speler genaamd {name}!")

    def do_tap(self, tappable_players: List) -> List:
        print(f"Je staat er als volgt voor: {self.get_hands}")
        if self.can_split:
            splitsen = input("Je kan splitsen. Doen? (J/N) ")
            if splitsen[0].lower() == "j":
                self.split
                return []
        
        print(f"De andere spelers staan er als volgt voor: {tappable_players}")
        wie = int(input("Wie wil je aantikken? "))
        welke = self.to_left_right(input("Welke hand van hen wil je tikken? "))
        mijn = self.to_left_right(input("Welke hand van jezelf wil je daarvoor gebruiken? "))

        return [wie, welke, self._fingers[mijn]]