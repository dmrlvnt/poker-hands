package com.casino.carddealer.evaluator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Game evaluator {@link GameEvaluator} test class.
 */
class GameEvaluatorTest {

  private static final String INPUT_VALIDATION_FORMAT = "%3$s %n";
  private Handler handler;
  private ByteArrayOutputStream out;

  @BeforeEach
  void setup() {
    Logger logger = Logger.getLogger(GameEvaluator.class.getName());
    SimpleFormatter formatter = new SimpleFormatter() {
      @Override
      public synchronized String format(LogRecord lr) {
        return String.format(INPUT_VALIDATION_FORMAT,
            ZonedDateTime.ofInstant(Instant.ofEpochMilli(lr.getMillis()),
                ZoneId.systemDefault()),
            lr.getLevel().getLocalizedName(),
            lr.getMessage()
        );
      }
    };
    out = new ByteArrayOutputStream();
    handler = new StreamHandler(out, formatter);
    logger.addHandler(handler);
  }

  @DisplayName("Royal Flush")
  @Test
  void testRoyalFlush() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("TC", "JC", "QC", "KC", "AC", "2C", "3C", "4C", "5C", "6C", "2C", "6C", "6D", "6H",
            "6S"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 1\tTC JC QC KC AC \tRoyal Flush, Clubs");
    assertThat(log).contains("2\tPlayer 2\t2C 3C 4C 5C 6C \tStraight Flush, Clubs");
    assertThat(log).contains("3\tPlayer 3\t2C 6C 6D 6H 6S \tFour of a Kind, Six");
    assertThat(log).contains("Player 1 wins.");
  }

  @DisplayName("Pair Of Eights")
  @Test
  void testPairOfEights() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("5H", "5C", "6S", "7S", "KD", "2C", "3S", "8S", "8D", "TD", "5D", "8C", "9S", "JS",
            "AC"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 2\t2C 3S 8D 8S TD \tOne Pair, Eight");
    assertThat(log).contains("2\tPlayer 1\t5C 5H 6S 7S KD \tOne Pair, Five");
    assertThat(log).contains("3\tPlayer 3\t5D 8C 9S JS AC \tHigh Card, Ace");
    assertThat(log).contains("Player 2 wins.");
  }

  @DisplayName("Highest Card Ace")
  @Test
  void testHighestCardAce() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("5D", "8C", "9S", "JS", "AC", "2C", "5C", "7D", "8S", "QH", "2C", "5C", "7D", "8S",
            "KH"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 1\t5D 8C 9S JS AC \tHigh Card, Ace");
    assertThat(log).contains("2\tPlayer 3\t2C 5C 7D 8S KH \tHigh Card, King");
    assertThat(log).contains("3\tPlayer 2\t2C 5C 7D 8S QH \tHigh Card, Queen");
    assertThat(log).contains("Player 1 wins.");
  }

  @DisplayName("Flush with Diamonds")
  @Test
  void testFlushWithDiamonds() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("2D", "9C", "AS", "AH", "AC", "3D", "6D", "7D", "TD", "QD", "2C", "5C", "7D", "8S",
            "QH"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 2\t3D 6D 7D TD QD \tFlush, Diamonds");
    assertThat(log).contains("2\tPlayer 1\t2D 9C AC AH AS \tThree of a Kind, Ace");
    assertThat(log).contains("3\tPlayer 3\t2C 5C 7D 8S QH \tHigh Card, Queen");
    assertThat(log).contains("Player 2 wins.");
  }

  @DisplayName("Pair of Queens")
  @Test
  void testPairOfQueens() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("4D", "6S", "9H", "QH", "QC", "3D", "6D", "7H", "QD", "QS", "2C", "5C", "7D", "8S",
            "QH"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 1\t4D 6S 9H QC QH \tOne Pair, Queen");
    assertThat(log).contains("2\tPlayer 2\t3D 6D 7H QD QS \tOne Pair, Queen");
    assertThat(log).contains("3\tPlayer 3\t2C 5C 7D 8S QH \tHigh Card, Queen");
    assertThat(log).contains("Player 1 wins.");
  }

  @DisplayName("Full House")
  @Test
  void testFullHouse() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("2H", "2D", "4C", "4D", "4S", "3C", "3D", "3S", "9S", "9D", "2C", "5C", "7D", "8S",
            "QH"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 1\t2D 2H 4C 4D 4S \tFull House, Four");
    assertThat(log).contains("2\tPlayer 2\t3C 3D 3S 9D 9S \tFull House, Three");
    assertThat(log).contains("3\tPlayer 3\t2C 5C 7D 8S QH \tHigh Card, Queen");
    assertThat(log).contains("Player 1 wins.");
  }

  @DisplayName("Double Royal Flush House")
  @Test
  void testDoubleRoyalFlush() {
    // Given
    final List<String> hands = new ArrayList<>(Arrays
        .asList("TC", "JC", "QC", "KC", "AC", "TS", "JS", "QS", "KS", "AS", "2C", "3C", "4C", "5C",
            "6C"));

    // When
    GameEvaluator ge = new GameEvaluator(hands);
    ge.evaluate();
    handler.flush();
    String log = out.toString();

    // Then
    assertThat(log).contains("1\tPlayer 1\tTC JC QC KC AC \tRoyal Flush, Clubs");
    assertThat(log).contains("1\tPlayer 2\tTS JS QS KS AS \tRoyal Flush, Spades");
    assertThat(log).contains("2\tPlayer 3\t2C 3C 4C 5C 6C \tStraight Flush, Clubs");
    assertThat(log).contains("Player 1, Player 2 win.");
  }

}
