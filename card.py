import random


# Singleton
class ClueCards:
    _instance = None
    _clue_cards = {
            "persons": [
                "Miss Scarlet",
                "Colonel Mustard",
                "Mrs. White",
                "Mr. Green",
                "Mrs. Peacock",
                "Professor Plum"
            ],
            "weapons": [
                "Candlestick",
                "Dagger",
                "Lead Pipe",
                "Revolver",
                "Rope",
                "Wrench"
            ],
            "rooms": [
                "Kitchen",
                "Ballroom",
                "Conservatory",
                "Dining Room",
                "Billiard Room",
                "Library",
                "Lounge",
                "Hall",
                "Study"
            ]
        }
    
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(ClueCards, cls).__new__(cls)
        return cls._instance

    def clue_card_list(self, type: str = "any" ) -> list[str]:
        lst = []
        if type in self._clue_cards:
            for card in self._clue_cards[type]:
                lst.append(f"{type_} {card}")
        else:
            for type_, cards in self._clue_cards.items():
                for card in cards:
                    lst.append(f"{type_} {card}")

        return lst
    
    @property
    def get_card_types(self) -> list[str]:
        return list(self._clue_cards.keys())

    # def get_random_card(self, type: str = "any", exclude: list[str] = [] ) -> str:
    def get_random_card(self, type: str = "any") -> str:
        if type in self._clue_cards:
            return f"{type} {random.choice(self._clue_cards[type])}"
        
        return random.choice(self.cue_card_list())

        
    





# # Example usage
# for card_type, cards in clue_cards.items():
#     print(f"{card_type.capitalize()} Cards: {', '.join(cards)}")



# class Card:
#     def __init__(self, type, name) -> None:
#         self.type = type
#         self.name = name