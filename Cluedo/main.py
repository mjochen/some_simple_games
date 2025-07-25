import logging
from game import Game

logging.basicConfig(level=logging.DEBUG)

print("Welcome to Cluedo in Python!")

# g = Game(["Jochen"], ["Lena", "Reggi", "Ronny"])
g = Game([], ["A_1", "Lena", "Reggi", "Ronny"])

try:
    while True:
        data = g.play_turn()
        print(data)
        if "won" in data.keys():
            break

except KeyboardInterrupt:
    print("KeyboardInterrupt caught!")
