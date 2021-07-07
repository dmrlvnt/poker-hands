package com.casino.carddealer.utility;

/**
 * Cards with their symbols and names; they are used used to display with the rank after the
 * evaluation.
 */
public enum Cards {
  TWO("2", "Two"),
  THREE("3", "Three"),
  FOUR("4", "Four"),
  FIVE("5", "Five"),
  SIX("6", "Six"),
  SEVEN("7", "Seven"),
  EIGHT("8", "Eight"),
  NINE("9", "Nine"),
  TEN("T", "Ten"),
  JACK("J", "Jack"),
  QUEEN("Q", "Queen"),
  KING("K", "King"),
  ACE("A", "Ace"),
  CLUBS("C", "Clubs"),
  DIAMONDS("D", "Diamonds"),
  HEARTS("H", "Hearts"),
  SPADES("S", "Spades");

  private final String symbol;
  private final String name;

  Cards(String symbol, String name) {
    this.symbol = symbol;
    this.name = name;
  }

  public static String findBySymbol(String symbol) {
    for (Cards c : values()) {
      if (c.symbol.equals(symbol)) {
        return c.name;
      }
    }
    return "";
  }

  @Override
  public String toString() {
    return symbol;
  }
}
