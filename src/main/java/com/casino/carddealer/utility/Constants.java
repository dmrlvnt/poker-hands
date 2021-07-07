package com.casino.carddealer.utility;

/**
 * Constants class.
 */
import java.util.List;

public class Constants {

  public static final List<String> CARD_RANK = List
      .of("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A");

  public static final List<String> CARD_SUIT = List.of("C", "D", "H", "S");

  public static final String CARD_PATTERN = "[2-9|TJQKA][CDHS]";

  public static final int MINIMUM_PLAYERS = 2;

  public static final int MAXIMUM_PLAYERS = 10;

  public static final int NUMBER_OF_ONE_HAND = 5;

  private Constants() {
    throw new IllegalStateException("Utility class");
  }

}
