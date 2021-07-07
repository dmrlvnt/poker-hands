package com.casino.carddealer.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Input validator {@link InputValidator} test class.
 */
class InputValidatorTest {

  ValidationResult vr;
  ErrorCodes[] ecs;

  @DisplayName("No hands given at all")
  @Test
  void testNoHandsAtAll() {
    // Given
    final String[] emptyArray = new String[0];

    // When
    vr = InputValidator.validateHands(emptyArray);
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isTrue();
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(1);
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (100): No hands given to evaluate.");
  }

  @DisplayName("No given hand blank string")
  @Test
  void testNoHandWithBlankString() {
    // Given
    final String nonEmptyBlankString = " ";

    // When
    vr = InputValidator.validateHands(new String[]{nonEmptyBlankString});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isTrue();
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(2);
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (101): At least 2 hands should be given to evaluate.");
    assertThat(
        ecs[1].toString())
        .hasToString("Game evaluation failed (103): A hand must consist of 5 cards.");
  }

  @DisplayName("No given hand empty string")
  @Test
  void testNoHandWithEmptyString() {
    // Given
    final String emptyString = "";

    // When
    vr = InputValidator.validateHands(new String[]{emptyString});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isTrue();
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(3);
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (101): At least 2 hands should be given to evaluate.");
    assertThat(
        ecs[1].toString())
        .hasToString("Game evaluation failed (103): A hand must consist of 5 cards.");
    assertThat(ecs[2].toString()).hasToString(
        "Game evaluation failed (104): A card is valid if it is represented as <card><suit>with <card> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A } and <suit> = { C, D, H, S }.");
  }

  @DisplayName("No given hand non blank string")
  @Test
  void testNoHandWithNonBlankString() {
    // Given
    final String nonEmptyNonBlankString = "foo";

    // When
    vr = InputValidator.validateHands(new String[]{nonEmptyNonBlankString});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isTrue();
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(3);
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (101): At least 2 hands should be given to evaluate.");
    assertThat(
        ecs[1].toString())
        .hasToString("Game evaluation failed (103): A hand must consist of 5 cards.");
    assertThat(ecs[2].toString()).hasToString(
        "Game evaluation failed (104): A card is valid if it is represented as <card><suit>with <card> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A } and <suit> = { C, D, H, S }.");
  }

  @DisplayName("Only one valid hand")
  @Test
  void testWithOneValidHand() {
    // Given
    final String validHand = "5H 5C 6S 7S KD";

    // When
    vr = InputValidator.validateHands(new String[]{validHand});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(5);
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(1);
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (101): At least 2 hands should be given to evaluate.");
  }

  @DisplayName("Only one invalid hand")
  @Test
  void testWithOneInvalidHand() {
    // Given
    final String invalidHand = "5H 5C 6S 7S KZ";

    // When
    vr = InputValidator.validateHands(new String[]{invalidHand});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(4);
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(2);
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (101): At least 2 hands should be given to evaluate.");
    assertThat(ecs[1].toString()).hasToString(
        "Game evaluation failed (104): A card is valid if it is represented as <card><suit>with <card> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A } and <suit> = { C, D, H, S }.");
  }

  @DisplayName("One valid, and one invalid hand")
  @Test
  void testWithOneValidOneInvalidHand() {
    // Given
    final String validHand = "5H 5C 6S 7S KD";
    final String invalidHand = "2C 3S 8S 8D TZ";

    // When
    vr = InputValidator.validateHands(new String[]{validHand, invalidHand});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(9);
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(vr.getErrorCodes().size()).isSameAs(1);
    assertThat(ecs[0].toString()).hasToString(
        "Game evaluation failed (104): A card is valid if it is represented as <card><suit>with <card> = { 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A } and <suit> = { C, D, H, S }.");
  }

  @DisplayName("Two valid hands")
  @Test
  void testWithTwoValidHand() {
    // Given
    final String validHand1 = "5H 5C 6S 7S KD";
    final String validHand2 = "2C 3S 8S 8D TD";

    // When
    vr = InputValidator.validateHands(new String[]{validHand1, validHand2});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(10);
    assertThat(vr.getErrorCodes().isEmpty()).isTrue();
  }

  @DisplayName("Three valid hands")
  @Test
  void testWithThreeValidHand() {
    // Given
    final String validHand1 = "5H 5C 6S 7S KD";
    final String validHand2 = "2C 3S 8S 8D TD";
    final String validHand3 = "5D 8C 9S JS AC";

    // When
    vr = InputValidator.validateHands(new String[]{validHand1, validHand2, validHand3});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(15);
    assertThat(vr.getErrorCodes().isEmpty()).isTrue();
  }

  @DisplayName("Two identical hands")
  @Test
  void testWithTwoIdenticalHand() {
    // Given
    final String validHand1 = "5H 5C 6S 7S KD";
    final String validHand2 = "5H 5C 6S 7S KD";

    // When
    vr = InputValidator.validateHands(new String[]{validHand1, validHand2});
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(5);
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(ecs[0].toString()).hasToString(
        "Game evaluation failed (105): In the standard 52-card deck, the cards are unique.");
  }

  @DisplayName("Eleven hands")
  @Test
  void testWithElevenHand() {
    // Given
    final String[] hands = new String[]{"5H 5C 6S 7S KD", "2C 3S 8S 8D TD", "5D 8C 9S JS AC",
        "5D 8C 9S JS AC", "2C 5C 7D 8S QH", "2C 5C 7D 8S KH", "2D 9C AS AH AC", "3D 6D 7D TD QD",
        "2C 5C 7D 8S QH", "4D 6S 9H QH QC", "3D 6D 7H QD QS"};

    // When
    vr = InputValidator.validateHands(hands);
    ecs = vr.getErrorCodes().toArray(new ErrorCodes[0]);

    // Then
    assertThat(vr.getCards().isEmpty()).isFalse();
    assertThat(vr.getCards().size()).isSameAs(28);
    assertThat(vr.getErrorCodes().isEmpty()).isFalse();
    assertThat(ecs[0].toString())
        .hasToString("Game evaluation failed (102): Maximum 10 hands can be evaluated for a game.");
    assertThat(ecs[1].toString()).hasToString(
        "Game evaluation failed (105): In the standard 52-card deck, the cards are unique.");
  }
}
