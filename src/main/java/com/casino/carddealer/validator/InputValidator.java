package com.casino.carddealer.validator;

import static com.casino.carddealer.utility.Constants.MAXIMUM_PLAYERS;
import static com.casino.carddealer.utility.Constants.MINIMUM_PLAYERS;
import static com.casino.carddealer.utility.Constants.NUMBER_OF_ONE_HAND;
import static com.casino.carddealer.utility.Constants.CARD_PATTERN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Validates the given poker hands by their number, length, and uniqueness. Returns {@link
 * ValidationResult} instance which holds validation errors {@link ErrorCodes} if any and all given
 * cards as a list.
 */
public interface InputValidator {

  static ValidationResult validateHands(String[] args) {
    List<String> cards = new ArrayList<>();
    Set<ErrorCodes> ecList = new LinkedHashSet<>();
    // Validate number of hands
    if (args.length == 0) {
      ecList.add(ErrorCodes.NO_HANDS);
    } else if (args.length < MINIMUM_PLAYERS) {
      ecList.add(ErrorCodes.AT_LEAST_TWO_HANDS);
    } else if (args.length > MAXIMUM_PLAYERS) {
      ecList.add(ErrorCodes.MAXIMUM_TEN_HANDS);
    }

    // Validate the number of cards in one hand
    Arrays.stream(args).limit(MAXIMUM_PLAYERS).forEach(hand -> {
      String[] cardsForHand = hand.split(" ");
      if (cardsForHand.length != NUMBER_OF_ONE_HAND) {
        ecList.add(ErrorCodes.WRONG_NUMBERS_OF_CARDS);
      }
      // Validate the card and uniqueness
      Arrays.stream(cardsForHand).limit(NUMBER_OF_ONE_HAND).forEach(card -> {
        if (!Pattern.matches(CARD_PATTERN, card)) {
          ecList.add(ErrorCodes.NOT_A_VALID_CARD);
        } else if (cards.contains(card)) {
          ecList.add(ErrorCodes.NOT_A_VALID_CARD_SET);
        } else {
          cards.add(card.toUpperCase());
        }
      });
    });

    return new ValidationResult(ecList, cards);
  }

}
