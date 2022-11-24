# Tema POO  - GwentStone

## src\ Structure
* src/
  * checker/ (from POO team)
  * fileio/ (from POO team)
  * game/ (contains classes related to the games functionality)
  * main/ (from POO team)
  * resurces/ (contains classes of different objects from the game)
    * cards/ (contains clases of the cards used)
      * environment/ (environment cards)
      * hero/ (hero cards)
      * minions/ (minion cards)
    * output/ (contains classes used to print the output)

The game begins in main where the LaunchGame.game method is called.

#### LaunchGame
We start running the game. To do this I used methods like: prepareGame, preparePlayers in the game method to modularize 
the code. Before every game I build the decks of the players and to prepare the game with inputs from the json file. 
After that the game starts.  After each game the values from the players and the board are reset;

The game consists of multiple rounds and rounds consist of two turns(Each player's turn)
The game reads commands from the json file executes them accordingly from their respective category.
* The debugging commands are written in the **DebugCommands Class**
* The statistics commands are written in the **StatsCommands Class**

#### Player
Each player saves his stats(mana, the hand, whether he is Player One or Player Two, if it is his turn, etc).
Besides different methods that use the stats, player also implements the placeCard method and the resetPlayer used 
after each game.

#### Deck
The Deck class contains the cards that form the deck. It also implements methods like shuffleCards and insertCardsInDeck
(Insert each cards class in the deck)

#### Board
The board class represents the table where the cards are placed. The notable methods implemented here are: the resetBoard 
used after each game and the convertOut(used to print the cards on the table the correct way)

#### MinionClass
Represents the pattern for all the minions. Besides getters and setters, the class contains the attackCard, attackHero, 
useAbility(implemented in each special minion) and convertOut

#### Hero
The Hero class represents the pattern for each hero. The useAbility method is implemented in each hero subclass, 
convertToOut being a common method

I used a boolean in order to know whether the present hero is the hero of Player One or Player Two, and another one to 
know if the heroAbility was used this turn

#### EnvironmentCard
Is the parent of all the environment cards. Each one implements the useCard method and their ability

## Conventions
* Through the files I used different booleans in order to keep track of which player's which.
  * Generally the false value means Player One, and the true value means Player Two
* We used another set of classes from the output package in order to replicate the output from the reference json files

