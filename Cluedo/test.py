import logging
from os import MFD_ALLOW_SEALING
from game import Game
from statistics import mean

# logging.basicConfig(level=logging.DEBUG)

print("Welcome to Cluedo in Python!")

players = ["Lena", "Reggi", "Ronny", "A_1"]

win_count = { p: [] for p in players }
for i in range(100):
    g = Game([], players)
    turns = 0
    while True:
        data = g.play_turn()
        if 'won' in data.keys():
            # print(data, turns)
            win_count[data["won"]].append(turns)
            break
        turns += 1

for key, val in win_count.items():
    if len(val) > 0:
        print(f"{key}: {len(val)}, {mean(val)}")
    else:
        print(f"{key}: 0")
