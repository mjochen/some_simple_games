import logging
from game import Game

logging.basicConfig(level=logging.DEBUG)

print("Welcome to Cluedo in Python!")

g = Game(["Jochen"], ["Lena", "Reggi", "Ronny"])

print(g.get_next_up())
print(g.get_next_up())
print(g.get_next_up())
print(g.get_next_up())
print(g.get_next_up())