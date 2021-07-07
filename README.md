This project aims to develop a command line application to evaluate a poker game.

## Game explained

In the card game poker, a hand consists of five cards. Hands are ranked, from lowest to highest, in
the following way:

- **High Card**: Highest value card.
- **One Pair**: Two cards of the same value.
- **Two Pairs**: Two different pairs.
- **Three of a Kind**: Three cards of the same value.
- **Straight**: All cards are consecutive values.
- **Flush**: All cards of the same suit.
- **Full House**: Three of a kind and a pair.
- **Four of a Kind**: Four cards of the same value.
- **Straight Flush**: All cards are consecutive values of same suit.
- **Royal Flush**: Ten, Jack, Queen, King, Ace, in same suit.

The cards are ranked, from lowest to highest, in the following order:
2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.

Each card has one of the following suits:
Clubs (C), Diamonds (D), Hearts (H) or Spades (S).

## Building

Requires Java 11 and Maven 3.8.0 (or greater)

```
$ mvn install
```

## Quick Start

Example:

```shell
$> java -jar poker-hands.jar "2D 9C AS AH AC" "3D 6D 7D TD QD" "2C 5C 7C 8S QH" ...
Ranking:
        1        Player 2        3D 6D 7D TD QD        Flush, Diamonds
        2        Player 1        2D 9C AS AH AC        Three Of a Kind, Ace
        3        Player 3        2C 5C 7C 8S QH        High Card, Queen
        
Player 2 wins.
```

Each argument passed to the application can be considered as a valid string representing a players
hand: each card is represented as <card><suit>
with <card> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A } and <suit> = { C, D, H, S }. Cards are
separated with a space. The application ranks each hand compared to the other hands and prints a
list of hands sorted by rank in descending order. Additionally, a description of each hand is
printed. Finally, the application prints the winner.

## Features

- Java 11 + JUnit 5
- Input validation
