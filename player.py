class Player:
    _cards = []
    _name = ""

    def __init__(self, name) -> None:
        self._name = name
        self._cards = []

    def receive_card(self, card: str) -> None:
        self._cards.append(card)

    def print_all_cards(self) -> None:
        print(self._name)
        print(*self._cards, sep=", ")


class HumanPlayer(Player):
    def __init__(self, name) -> None:
        super().__init__(name)
        print(f"Welcome human player called {name}")

class ComputerPlayer(Player):
    def __init__(self, name) -> None:
        super().__init__(name)
        print(f"Added computer player called {name}")