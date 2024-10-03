import logging
from game import Game

logging.basicConfig(level=logging.DEBUG)

print("Welcome to Cluedo in Python!")

g = Game(["Jochen"], ["Lena", "Reggi", "Ronny"])

try:
    while data := g.play_turn():
        print(data)
except KeyboardInterrupt:
    print("KeyboardInterrupt caught!")
