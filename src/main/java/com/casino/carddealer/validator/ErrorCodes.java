package com.casino.carddealer.validator;

/**
 * Provides validation error codes with their code, and description.
 */
public enum ErrorCodes {
  NO_HANDS(100, "No hands given to evaluate."),
  AT_LEAST_TWO_HANDS(101, "At least 2 hands should be given to evaluate."),
  MAXIMUM_TEN_HANDS(102, "Maximum 10 hands can be evaluated for a game."),
  WRONG_NUMBERS_OF_CARDS(103, "A hand must consist of 5 cards."),
  NOT_A_VALID_CARD(104, "A card is valid if it is represented as <card><suit>"
      + "with <card> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A } and <suit> = { C, D, H, S }."),
  NOT_A_VALID_CARD_SET(105, "In the standard 52-card deck, the cards are unique.");

  private final int code;
  private final String description;

  ErrorCodes(int code, String description) {
    this.code = code;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Game evaluation failed (" + code + "): " + description;
  }

}
