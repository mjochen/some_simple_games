from board import Board

class Player:
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


class HumanPlayer(Player):
    def __init__(self, name) -> None:
        super().__init__(name)
        print(f"Welcome human player called {name}!")
        print(f"Player is standing in the {self._position}.")

class ComputerPlayer(Player):
    def __init__(self, name) -> None:
        super().__init__(name)
        print(f"Added computer player called {name}!")
        print(f"Player is standing in the {self._position}.")