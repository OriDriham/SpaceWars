# Space Wars Game

Welcome to the Space Wars game!\
This interactive space battle allows you to control a human spaceship against a variety of enemy ships.

![spaceWarsExample](https://github.com/OriDriham/SpaceWars/assets/145263130/a5cfaff0-67c0-41fe-996a-786fbca151b2)


# File Descriptions

### AggressiveShip.java:
A class representing a ship that pursues other ships and fires at them by always accelerating and turning
towards the nearest ship.

### BasherShip.java:
A class representing a ship that aims to collide with other ships by accelerating and constantly turning
towards the closest ship.

### DrunkardShip.java:
A class representing a ship whose pilot has consumed too much alcohol.\
This ship runs away from battles, randomly firing or activating its shield for 10 continuous rounds before reevaluating.

### HumanControlledShip.java:
A class implementing a human-controlled ship, allowing user input to control the spaceship.

### RunnerShip.java:
A class representing a spaceship that attempts to escape from battles by constantly accelerating and turning
away from the closest ship.

### SpaceShip.java:
An abstract class defining and implementing basic actions shared among different spaceship types, such as the
option to activate its shield.

### SpaceShipFactory.java:
A class with a single static method used by the driver to create all spaceship objects based on command line
arguments.

### SpaceWars.java:
This file serves as the driver for the Space Wars game.\
It orchestrates the gameplay, manages spaceships, shots, collisions, and interactions between various spaceship types.

### SpecialShip.java:
A class representing a unique ship that pursues other ships, fires at them, teleports when threatened, and
activates its shield when a ship gets too close. It combines defensive and aggressive strategies.


# Usage

To get started, provide the program with at least 2 arguments, with the first being
the human ship and the rest being your choice of enemy ships.\
Choose from the available spaceship types:

```
h - Human
d - Drunkard
r - Runner
a - Aggressive
b - Basher
s - Special
```

For example, entering the argument line:
```sh
h r a
```
will initiate the game with one human ('h') ship under your control, and two enemy ships: a runner ('r') and
an aggressive ('a') ship.


# Game Controls

Navigate your spaceship through the cosmic battlefield with the following controls:

| keys | Description |
| :---: | --- |
| Arrows | Move in all directions |
| A | Teleport |
| S | Activate Shield (while pressed) |
| D | Shoot |


# Tips and "Hacks"

- Use teleport wisely, as it has limitations.
- Observe computer-controlled battles by excluding the human ship from the arguments.
- Experiment by choosing the human ship multiple times to discover what happens.


# Design

The SpaceWars class serves as the driver for the game, utilizing methods from the SpaceShipFactory and SpaceShip classes.\
The abstract SpaceShip class provides a foundation for different ship types, with subclasses extending its functionalities.\
This design minimizes code duplication and allows for easy modifications to overall ship behavior.


# Implementation Details

### Drunkard Ship:
The Drunkard ship exhibits erratic behavior, randomly choosing between firing and activating its shield for 10 continuous rounds before reassessing.\
It constantly accelerates and turns away from the closest ship.

### Special Ship:
The Special ship is both defensive and aggressive, pursuing other ships, firing at them, teleporting when threatened, and activating its shield when in close proximity to other ships.\
It always accelerates and turns towards the nearest ship.


### Enjoy the Space Wars game, and may the best pilot prevail in the cosmic arena!
