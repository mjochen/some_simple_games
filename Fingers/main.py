import logging
from game import Game

logging.basicConfig(level=logging.DEBUG)

print("Welkom bij het gekke vinger-spel!")

# g = Game(["Jochen"], ["Lena", "Reggi", "Ronny"])
g = Game(["Papa", "Kasper"], [])

try:
    while g.get_nr_remaining_players() > 1:
        g.turn()

except KeyboardInterrupt:
    print("KeyboardInterrupt caught!")
