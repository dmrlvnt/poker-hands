package com.casino.carddealer.evaluator;

/**
 * Ranks with their hierarchy, name, and description.
 */
public enum Ranks {
  HIGH_CARD(1, "High Card", "Highest value card"),
  ONE_PAIR(2, "One Pair", "Two cards of the same value"),
  TWO_PAIRS(3, "Two Pairs", "Two different pairs"),
  THREE_OF_A_KIND(4, "Three of a Kind", "Three cards of the same value"),
  STRAIGHT(5, "Straight", "All cards are consecutive values"),
  FLUSH(6, "Flush", "All cards of the same suit"),
  FULL_HOUSE(7, "Full House", "Three of a kind and a pair"),
  FOUR_OF_A_KIND(8, "Four of a Kind", "Four cards of the same value"),
  STRAIGHT_FLUSH(9, "Straight Flush", "All cards are consecutive values of same suit"),
  ROYAL_FLUSH(10, "Royal Flush", "Ten, Jack, Queen, King, Ace, in same suit");

  private final int hierarchy;
  private final String name;
  private final String description;

  Ranks(int hierarchy, String name, String description) {
    this.hierarchy = hierarchy;
    this.name = name;
    this.description = description;
  }

  public int getHierarchy() {
    return hierarchy;
  }

  @Override
  public String toString() {
    return name;
  }

}
