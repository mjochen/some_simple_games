from board import Board
from card import ClueCards
from abc import ABC, abstractmethod
import random

class Player(ABC):
    _cards = []
    _name = ""
    _position = ""

    def __init__(self, name) -> None:
        self._name = name
        self._cards = []
        self._position = Board().get_random_room()

    def receive_card(self, card: str) -> None:
        self._cards.append(card)

    @property
    def get_all_cards(self) -> list[str]:
        return self._cards

    @property
    def name(self) -> str:
        return self._name

    @property
    def position(self) -> str:
        return self._position

    @abstractmethod
    def move(self, dice_throw: int) -> str:
        pass

    @abstractmethod
    def ask_cards(self) -> list[str]:
        pass

    def show_card(self, cards: list[str]) -> str:
        for c in cards:
            if c in self._cards:
                return c
        return ""

    @abstractmethod
    def see_card(self, card: str, playername: str) -> bool:
        pass

    @abstractmethod
    def guess(self) -> list[str]:
        pass

class HumanPlayer(Player):
    _seen_cards = []
    _last_shown = []
    _guess = []

    def __init__(self, name) -> None:
        super().__init__(name)
        print(f"Welcome human player called {name}!")
        print(f"Player is standing in the {self._position}.")

    def move(self, dice_throw: int) -> str:
        move_options = Board().get_possible_rooms(self._position, dice_throw)

        for i, p in enumerate(move_options):
            print(i,p)

        p_i = int(input("Were do you want to move? "))
        self._position = move_options[p_i]
        return self._position

    def ask_cards(self) -> list[str]:
        cards = [f"rooms {self._position}"]
        for type in ["persons", "weapons"]:
            all_cards = ClueCards().clue_card_list(type)
            all_cards = [ c for c in all_cards if c not in self._cards]
            all_cards = [ c for c in all_cards if c not in self._seen_cards]

            for i, c in enumerate(all_cards):
                print(i,c)
            ind = int(input("Choose which card? "))
            cards.append(all_cards[ind])

        self._last_shown = cards
        return cards

    def see_card(self, card: str, playername: str) -> bool:
        print(card, playername)
        if len(card) > 0:
            self._seen_cards.append(card)
            print(self._seen_cards)
            print(self._cards)
        else:
            if input("Nobody had those. Want to take a guess? (Y/N) ") == "Y":
                self._guess = self._last_shown
        return True

    def guess(self) -> list[str]:
        guess = self._guess
        self._guess = []
        return guess

class ComputerPlayer(Player):
    _seen_cards = []

    def __init__(self, name) -> None:
        super().__init__(name)
        print(f"Added computer player called {name}!")
        print(f"Player is standing in the {self._position}.")

    def move(self, dice_throw: int) -> str:
        move_options = Board().get_possible_rooms(self._position, dice_throw)
        self._position = random.choice(move_options)
        return self._position

    def ask_cards(self) -> list[str]:
        cards = [f"rooms {self._position}"]
        for type in ["persons", "weapons"]:
            all_cards = ClueCards().clue_card_list(type)
            all_cards = [ c for c in all_cards if c not in self._cards]
            all_cards = [ c for c in all_cards if c not in self._seen_cards]

            cards.append(random.choice(all_cards))
        return cards

    def see_card(self, card: str, playername: str) -> bool:
        if len(card) > 0:
            self._seen_cards.append(card)
        return True

    def guess(self) -> list[str]:
        all_cards = ClueCards().clue_card_list()
        all_cards = [ c for c in all_cards if c not in self._cards]
        all_cards = [ c for c in all_cards if c not in self._seen_cards]
        if len(all_cards) == 3:
            return all_cards
        return []
