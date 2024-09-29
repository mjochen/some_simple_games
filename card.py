# List of Clue/Cluedo cards categorized by type



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

    @property
    def cue_card_list(self):
        lst = []
        for type_, cards in self._clue_cards.items():
            for card in cards:
                lst.append(f"{type_} {card}")

        return lst





# # Example usage
# for card_type, cards in clue_cards.items():
#     print(f"{card_type.capitalize()} Cards: {', '.join(cards)}")



# class Card:
#     def __init__(self, type, name) -> None:
#         self.type = type
#         self.name = name