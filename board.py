import random


# Singleton
class Board:
    _steps_between_rooms = {
        ('Ballroom', 'Billiard Room'): 6,
        ('Ballroom', 'Conservatory'): 4,
        ('Ballroom', 'Dining Room'): 7,
        ('Ballroom', 'Hall'): 13,
        ('Ballroom', 'Kitchen'): 7,
        ('Ballroom', 'Library'): 12,
        ('Ballroom', 'Lounge'): 5,
        ('Ballroom', 'Study'): 8,
        ('Billiard Room', 'Conservatory'): 7,
        ('Billiard Room', 'Dining Room'): 14,
        ('Billiard Room', 'Hall'): 15,
        ('Billiard Room', 'Kitchen'): 17,
        ('Billiard Room', 'Library'): 4,
        ('Billiard Room', 'Lounge'): 8,
        ('Billiard Room', 'Study'): 15,
        ('Conservatory', 'Dining Room'): 5,
        ('Conservatory', 'Hall'): 9,
        ('Conservatory', 'Kitchen'): 20,
        ('Conservatory', 'Library'): 14,
        ('Conservatory', 'Lounge'): 1,
        ('Conservatory', 'Study'): 20,
        ('Dining Room', 'Hall'): 8,
        ('Dining Room', 'Kitchen'): 11,
        ('Dining Room', 'Library'): 14,
        ('Dining Room', 'Lounge'): 4,
        ('Dining Room', 'Study'): 12,
        ('Hall', 'Kitchen'): 5,
        ('Hall', 'Library'): 7,
        ('Hall', 'Lounge'): 8,
        ('Hall', 'Study'): 4,
        ('Kitchen', 'Library'): 8,
        ('Kitchen', 'Lounge'): 19,
        ('Kitchen', 'Study'): 1,
        ('Library', 'Lounge'): 14,
        ('Library', 'Study'): 7,
        ('Lounge', 'Study'): 17,
    }
    _rooms = [
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

    def __init__(self) -> None:
        pass

    def get_possible_rooms(self, current_room: str, nr_of_steps: int) -> list[str]:
        lst = []
        for key, value in self._steps_between_rooms.items():
            print(key, value)
            if current_room in key and value <= nr_of_steps:
                k = list(key)
                k.remove(current_room)
                lst.append(k[0])
        return lst

    def get_random_room(self) -> str:
        return random.choice(self._rooms)
